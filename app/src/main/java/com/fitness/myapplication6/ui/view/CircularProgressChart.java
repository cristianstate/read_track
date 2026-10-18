package com.fitness.myapplication6.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.fitness.myapplication6.R;

/**
 * Custom View care desenază un grafic circular de progres și afișează procentul în centru.
 */
public class CircularProgressChart extends View {

    // Paint pentru cercul de fundal (inactiv)
    private Paint backgroundPaint;

    // Paint pentru arcul de progres (activ)
    private Paint progressPaint;

    // Paint pentru afișarea textului cu procentajul
    private Paint textPaint;

    // Dreptunghiul de încadrare al cercului
    private RectF rectF;

    // Unghiul de progres în grade (0 - 360)
    private float progress = 0;

    // Textul afișat în centrul graficului
    private String progressText = "0%";

    public CircularProgressChart(Context context) {
        super(context);
        init(context);
    }

    public CircularProgressChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * Inițializează proprietățile de desenare (Paint, culori, dimensiuni).
     */
    private void init(Context context) {
        // Configurarea stilului pentru cercul de fundal
        backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundPaint.setStyle(Paint.Style.STROKE);
        backgroundPaint.setStrokeWidth(20);
        backgroundPaint.setColor(ContextCompat.getColor(context, android.R.color.darker_gray));

        // Configurarea stilului pentru arcul de progres
        progressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setStrokeWidth(20);
        progressPaint.setStrokeCap(Paint.Cap.ROUND);
        progressPaint.setColor(ContextCompat.getColor(context, R.color.purple_500));

        // Configurarea stilului pentru textul procentual
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(ContextCompat.getColor(context, android.R.color.black));
        textPaint.setTextSize(40);
        textPaint.setTextAlign(Paint.Align.CENTER);

        rectF = new RectF();
    }

    /**
     * Setează progresul curent și redesenază componenta.
     *
     * @param current Pagina curentă
     * @param total   Numărul total de pagini
     */
    public void setProgress(int current, int total) {
        if (total > 0) {
            this.progress = (float) current / total * 360;
            this.progressText = (int) ((float) current / total * 100) + "%";
        } else {
            this.progress = 0;
            this.progressText = "0%";
        }
        // Solicită redesenarea View-ului
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float width = getWidth();
        float height = getHeight();
        float size = Math.min(width, height) - 40;

        // Calculăm limitele dreptunghiului de desenare
        rectF.set((width - size) / 2, (height - size) / 2, (width + size) / 2, (height + size) / 2);

        // Desenează cercul de fundal
        canvas.drawOval(rectF, backgroundPaint);

        // Desenează arcul de progres pornind de la ora 12 (-90 de grade)
        canvas.drawArc(rectF, -90, progress, false, progressPaint);

        // Calculează poziția verticală a textului și îl desenează în centru
        float textHeight = textPaint.descent() + textPaint.ascent();
        canvas.drawText(progressText, width / 2, (height - textHeight) / 2, textPaint);
    }
}
