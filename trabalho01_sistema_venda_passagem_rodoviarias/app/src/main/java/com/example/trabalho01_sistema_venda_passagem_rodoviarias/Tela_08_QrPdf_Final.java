package com.example.trabalho01_sistema_venda_passagem_rodoviarias;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Tela_08_QrPdf_Final extends AppCompatActivity {

    private ImageView ivQrCode;
    private TextView tvInformacoesPassagem, tvStatus;
    private Button btnGerarPDF, btnCompartilhar, btnFinalizar;
    private SharedPreferences preferences;
    private Bitmap qrCodeBitmap;
    private String codigoReserva;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela08_qr_pdf_final);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        preferences = getSharedPreferences("PassageirosPref", MODE_PRIVATE);

        initViews();
        gerarCodigoReserva();
        gerarQRCodeReal(); // Método com ZXing
        exibirInformacoesPassagem();
        setupClickListeners();
    }

    private void initViews() {
        ivQrCode = findViewById(R.id.ivQrCode);
        tvInformacoesPassagem = findViewById(R.id.tvInformacoesPassagem);
        tvStatus = findViewById(R.id.tvStatus);
        btnGerarPDF = findViewById(R.id.btnGerarPDF);
        btnCompartilhar = findViewById(R.id.btnCompartilhar);
        btnFinalizar = findViewById(R.id.btnFinalizar);
    }

    private void gerarCodigoReserva() {
        codigoReserva = "PASS" + System.currentTimeMillis();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("codigo_reserva", codigoReserva);
        editor.apply();
    }

    private void gerarQRCodeReal() {
        try {
            // Dados que serão codificados no QR Code
            String qrData = criarDadosQRCode();

            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            BitMatrix bitMatrix = multiFormatWriter.encode(qrData, BarcodeFormat.QR_CODE, 500, 500);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            qrCodeBitmap = barcodeEncoder.createBitmap(bitMatrix);

            ivQrCode.setImageBitmap(qrCodeBitmap);
            Log.d("QR_CODE", "QR Code gerado com sucesso: " + qrData);

        } catch (WriterException e) {
            Log.e("QR_CODE", "Erro ao gerar QR Code: " + e.getMessage());
            gerarQRCodeSimples(); // Fallback
        } catch (Exception e) {
            Log.e("QR_CODE", "Erro inesperado: " + e.getMessage());
            gerarQRCodeSimples(); // Fallback
        }
    }

    private String criarDadosQRCode() {
        StringBuilder dados = new StringBuilder();
        dados.append("PASSAGEM RODOVIÁRIA\n");
        dados.append("Código: ").append(codigoReserva).append("\n");
        dados.append("Origem: ").append(preferences.getString("origem", "")).append("\n");
        dados.append("Destino: ").append(preferences.getString("destino", "")).append("\n");
        dados.append("Data: ").append(preferences.getString("data", "")).append("\n");
        dados.append("Horário: ").append(preferences.getString("horario", "")).append("\n");
        dados.append("Passageiros: ").append(preferences.getInt("passageiro_count", 0)).append("\n");
        dados.append("Valor: R$ ").append(String.format("%.2f", preferences.getFloat("valor", 0)));

        return dados.toString();
    }

    private void gerarQRCodeSimples() {
        try {
            String qrData = "RESERVA: " + codigoReserva;
            qrCodeBitmap = criarQRCodeSimples(qrData, 500);
            if (qrCodeBitmap != null) {
                ivQrCode.setImageBitmap(qrCodeBitmap);
            }
        } catch (Exception e) {
            Log.e("QR_SIMPLE", "Erro fallback: " + e.getMessage());
        }
    }

    private Bitmap criarQRCodeSimples(String text, int size) {
        try {
            Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            canvas.drawColor(Color.WHITE);

            Paint paint = new Paint();
            paint.setColor(Color.BLACK);
            paint.setTextSize(40);
            paint.setTextAlign(Paint.Align.CENTER);

            canvas.drawText(text, size / 2, size / 2, paint);

            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(5);
            canvas.drawRect(0, 0, size, size, paint);

            return bitmap;
        } catch (Exception e) {
            return null;
        }
    }

    private void exibirInformacoesPassagem() {
        StringBuilder info = new StringBuilder();

        // Dados da viagem - Código com quebra de linha
        info.append("🎫 CÓDIGO DA RESERVA\n");
        info.append("────────────────────\n");
        info.append(codigoReserva).append("\n\n");

        info.append("📍 Origem: ").append(preferences.getString("origem", "N/A")).append("\n");
        info.append("🎯 Destino: ").append(preferences.getString("destino", "N/A")).append("\n");
        info.append("📅 Data: ").append(preferences.getString("data", "N/A")).append("\n");
        info.append("⏰ Horário: ").append(preferences.getString("horario", "N/A")).append("\n");
        info.append("🚌 Empresa: ").append(preferences.getString("empresa", "N/A")).append("\n");
        info.append("💺 Assentos: ").append(preferences.getInt("assentos_reservados", 0)).append("\n");
        info.append("💳 Pagamento: ").append(preferences.getString("forma_pagamento", "N/A")).append("\n");
        info.append("💰 Valor: R$ ").append(String.format("%.2f", preferences.getFloat("valor", 0))).append("\n\n");

        // Passageiros
        int passageiroCount = preferences.getInt("passageiro_count", 0);
        info.append("👥 Passageiros: ").append(passageiroCount).append("\n");

        for (int i = 0; i < passageiroCount; i++) {
            info.append("  • ").append(preferences.getString("nome_" + i, "")).append("\n");
        }

        tvInformacoesPassagem.setText(info.toString());
    }

    private void setupClickListeners() {
        btnGerarPDF.setOnClickListener(v -> gerarPDF());
        btnCompartilhar.setOnClickListener(v -> compartilharPassagem());
        btnFinalizar.setOnClickListener(v -> finalizarApp());
    }

    private void gerarPDF() {
        try {
            PdfDocument document = new PdfDocument();
            PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(595, 842, 1).create();
            PdfDocument.Page page = document.startPage(pageInfo);
            Canvas canvas = page.getCanvas();

            drawPDFContent(canvas);

            document.finishPage(page);

            File pdfFile = salvarPDF(document);
            document.close();

            if (pdfFile != null) {
                tvStatus.setText("✅ PDF salvo em: Downloads/" + pdfFile.getName());
                Toast.makeText(this, "PDF com QR Code gerado com sucesso!", Toast.LENGTH_LONG).show();

                // NOVO: Abrir o PDF automaticamente
                abrirPDF(pdfFile);
            } else {
                tvStatus.setText("❌ Erro ao salvar PDF");
                Toast.makeText(this, "Erro ao salvar PDF", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Log.e("PDF_GERATION", "Erro ao gerar PDF: " + e.getMessage());
            Toast.makeText(this, "Erro ao gerar PDF: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            tvStatus.setText("❌ Erro ao gerar PDF");
        }
    }

    // NOVO MÉTODO: Abrir o PDF após gerar
    private void abrirPDF(File pdfFile) {
        try {
            // Verificar se o arquivo existe
            if (pdfFile.exists()) {
                // Criar URI para o arquivo
                Uri pdfUri = FileProvider.getUriForFile(this,
                        getApplicationContext().getPackageName() + ".provider", pdfFile);

                // Intent para visualizar PDF
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(pdfUri, "application/pdf");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                // Verificar se há app para abrir PDF
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    // Se não há app de PDF, oferecer para baixar um
                    Toast.makeText(this, "Instale um leitor de PDF para visualizar o arquivo", Toast.LENGTH_LONG).show();
                    abrirLojaPDF();
                }
            } else {
                Toast.makeText(this, "Arquivo PDF não encontrado", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e("ABRIR_PDF", "Erro ao abrir PDF: " + e.getMessage());
            Toast.makeText(this, "Erro ao abrir PDF: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    // MÉTODO AUXILIAR: Sugerir app de PDF
    private void abrirLojaPDF() {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://play.google.com/store/search?q=pdf%20reader&c=apps"));
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Pesquise por 'PDF Reader' na Play Store", Toast.LENGTH_LONG).show();
        }
    }

    private void drawPDFContent(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);

        int yPosition = 80;

        // Cabeçalho
        paint.setTextSize(22);
        paint.setFakeBoldText(true);
        paint.setColor(Color.parseColor("#2E7D32")); // Verde escuro
        canvas.drawText("PASSAGEM RODOVIÁRIA", 50, yPosition, paint);
        yPosition += 40;

        // Linha divisória
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2);
        canvas.drawLine(50, yPosition, 545, yPosition, paint);
        yPosition += 20;

        // Informações da viagem
        paint.setTextSize(12);
        paint.setFakeBoldText(false);

        canvas.drawText("CÓDIGO: " + codigoReserva, 50, yPosition, paint);
        yPosition += 20;

        paint.setFakeBoldText(true);
        canvas.drawText("INFORMAÇÕES DA VIAGEM:", 50, yPosition, paint);
        yPosition += 20;
        paint.setFakeBoldText(false);

        canvas.drawText("• Origem: " + preferences.getString("origem", ""), 60, yPosition, paint);
        yPosition += 15;
        canvas.drawText("• Destino: " + preferences.getString("destino", ""), 60, yPosition, paint);
        yPosition += 15;
        canvas.drawText("• Data: " + preferences.getString("data", ""), 60, yPosition, paint);
        yPosition += 15;
        canvas.drawText("• Horário: " + preferences.getString("horario", ""), 60, yPosition, paint);
        yPosition += 15;
        canvas.drawText("• Empresa: " + preferences.getString("empresa", ""), 60, yPosition, paint);
        yPosition += 15;
        canvas.drawText("• Assentos: " + preferences.getInt("assentos_reservados", 0), 60, yPosition, paint);
        yPosition += 15;
        canvas.drawText("• Valor: R$ " + String.format("%.2f", preferences.getFloat("valor", 0)), 60, yPosition, paint);
        yPosition += 25;

        // Passageiros
        paint.setFakeBoldText(true);
        canvas.drawText("PASSAGEIROS:", 50, yPosition, paint);
        yPosition += 20;
        paint.setFakeBoldText(false);

        int passageiroCount = preferences.getInt("passageiro_count", 0);
        for (int i = 0; i < passageiroCount; i++) {
            String nome = preferences.getString("nome_" + i, "");
            String documento = preferences.getString("documento_" + i, "");
            canvas.drawText("▪ " + nome + " - " + documento, 60, yPosition, paint);
            yPosition += 12;
        }

        yPosition += 30;

        // QR Code no PDF
        if (qrCodeBitmap != null) {
            paint.setFakeBoldText(true);
            canvas.drawText("QR CODE PARA VALIDAÇÃO:", 50, yPosition, paint);
            yPosition += 20;
            paint.setFakeBoldText(false);

            // Redimensionar QR Code para o PDF
            Bitmap scaledQr = Bitmap.createScaledBitmap(qrCodeBitmap, 200, 200, true);
            canvas.drawBitmap(scaledQr, 200, yPosition, paint);
            yPosition += 220;

            paint.setTextSize(10);
            canvas.drawText("Escaneie este QR Code para validar a passagem", 150, yPosition, paint);
            yPosition += 15;
            paint.setTextSize(12);
        }

        // Rodapé
        yPosition += 30;
        paint.setStrokeWidth(1);
        canvas.drawLine(50, yPosition, 545, yPosition, paint);
        yPosition += 15;

        paint.setTextSize(9);
        paint.setColor(Color.GRAY);
        String dataEmissao = new SimpleDateFormat("dd/MM/yyyy 'às' HH:mm", Locale.getDefault()).format(new Date());
        canvas.drawText("Emitido em " + dataEmissao + " - Sistema de Passagens Rodoviárias",
                50, yPosition, paint);

    }

    private File salvarPDF(PdfDocument document) {
        FileOutputStream fos = null;
        try {
            File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            if (!downloadsDir.exists()) {
                boolean created = downloadsDir.mkdirs();
                Log.d("PDF_SAVE", "Pasta Downloads criada: " + created);
            }

            String fileName = "Passagem_" + codigoReserva + ".pdf";
            File file = new File(downloadsDir, fileName);

            fos = new FileOutputStream(file);
            document.writeTo(fos);

            Log.d("PDF_SAVE", "PDF salvo com sucesso: " + file.getAbsolutePath());
            Log.d("PDF_SAVE", "Tamanho do arquivo: " + file.length() + " bytes");

            return file;

        } catch (IOException e) {
            Log.e("PDF_SAVE", "Erro ao salvar PDF: " + e.getMessage());
            return null;
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    Log.e("PDF_SAVE", "Erro ao fechar stream: " + e.getMessage());
                }
            }
        }
    }

    private void compartilharPassagem() {
        try {
            File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File[] files = downloadsDir.listFiles((dir, name) -> name.startsWith("Passagem_" + codigoReserva));

            if (files != null && files.length > 0) {
                File pdfFile = files[0];

                Uri pdfUri = FileProvider.getUriForFile(this,
                        getApplicationContext().getPackageName() + ".provider", pdfFile);

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("application/pdf");
                shareIntent.putExtra(Intent.EXTRA_STREAM, pdfUri);
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Passagem Rodoviária - " + codigoReserva);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Confira sua passagem rodoviária com QR Code!");
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                startActivity(Intent.createChooser(shareIntent, "Compartilhar Passagem"));

            } else {
                Toast.makeText(this, "Gere o PDF primeiro", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Log.e("SHARE_PDF", "Erro ao compartilhar: " + e.getMessage());
            Toast.makeText(this, "Erro ao compartilhar", Toast.LENGTH_SHORT).show();
        }
    }

    private void finalizarApp() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}