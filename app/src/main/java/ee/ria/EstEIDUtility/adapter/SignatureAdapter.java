package ee.ria.EstEIDUtility.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.x500.style.BCStyle;

import java.io.File;
import java.util.List;

import ee.ria.EstEIDUtility.R;
import ee.ria.EstEIDUtility.domain.X509Cert;
import ee.ria.EstEIDUtility.util.DateUtils;
import ee.ria.EstEIDUtility.util.FileUtils;
import ee.ria.libdigidocpp.Container;
import ee.ria.libdigidocpp.Signature;

public class SignatureAdapter extends ArrayAdapter<Signature> implements Filterable {

    private AlertDialog confirmDialog;
    private String bdocFileName;
    private Context context;

    private static class ViewHolder {
        TextView name;
        TextView signed;
        ImageView removeSignature;
        TextView isSigned;
    }

    public SignatureAdapter(Context context, List<Signature> signatures, String bdocFileName) {
        super(context, 0, signatures);
        this.context = context;
        this.bdocFileName = bdocFileName;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_bdoc_detail_signatures, parent, false);

            viewHolder.name = (TextView) convertView.findViewById(R.id.personName);
            viewHolder.signed = (TextView) convertView.findViewById(R.id.fileSize);
            viewHolder.isSigned = (TextView) convertView.findViewById(R.id.isSigned);
            viewHolder.removeSignature = (ImageView) convertView.findViewById(R.id.removeSignature);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Signature signature = getItem(position);

        X509Cert x509Cert = new X509Cert(signature.signingCertificateDer());

        String surname = x509Cert.getValueByObjectIdentifier(ASN1ObjectIdentifier.getInstance(BCStyle.SURNAME));
        String name = x509Cert.getValueByObjectIdentifier(ASN1ObjectIdentifier.getInstance(BCStyle.GIVENNAME));
        String serialNumber = x509Cert.getValueByObjectIdentifier(ASN1ObjectIdentifier.getInstance(BCStyle.SERIALNUMBER));

        final String personInfo = String.format("%s %s (%s)", name, surname, serialNumber);
        viewHolder.name.setText(personInfo);
        viewHolder.signed.setText(DateUtils.formatSignedDate(signature.trustedSigningTime()));

        if (x509Cert.isValid()) {
            String t = getContext().getResources().getString(R.string.signature_valid);
            viewHolder.isSigned.setText(t);
            viewHolder.isSigned.setTextColor(Color.GREEN);
        } else {
            String t = getContext().getResources().getString(R.string.signature_invalid);
            viewHolder.isSigned.setText(t);
            viewHolder.isSigned.setTextColor(Color.RED);
        }

        viewHolder.removeSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(R.string.bdoc_remove_confirm_title);

                String confirmMessage = getContext().getResources().getString(R.string.signature_remove_confirm_message);
                confirmMessage = String.format(confirmMessage, personInfo);

                builder.setMessage(confirmMessage);

                builder.setPositiveButton(R.string.confirm_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Signature sign = getItem(position);

                        Container container = FileUtils.getContainer(context.getFilesDir(), bdocFileName);
                        container.removeSignature(position);

                        File bdocFile = FileUtils.getBdocFile(context.getFilesDir(), bdocFileName);
                        container.save(bdocFile.getAbsolutePath());
                        remove(sign);
                        notifyDataSetChanged();
                    }
                }).setNegativeButton(R.string.cancel_button, null);

                confirmDialog = builder.create();
                confirmDialog.show();
            }
        });

        return convertView;
    }

}
