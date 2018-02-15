import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JApplet;

/*
    Nguyen, Nguyen
    CS A170
    02/12/2018

    IC04_Snowman
 */

public class Snowman extends JApplet {
	private static final long serialVersionUID = 1L;
	// Fields
	private final int CANVAS_WIDTH = 250, CANVAS_HEIGHT = 400;

	// Initialize
	public void init() {
		// Set the canvas size.
		setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
	}

	// Paint
	public void paint(Graphics canvas) {
		// Variables
		Point startingPosition = new Point(100, 50);

		// Face
		Circle headSection = new Circle(startingPosition, 35, 2, Color.BLACK, null);

		// Eyes
		Point leftEyeCenter = new Point(headSection.Center.x - headSection.Radius / 3,
				headSection.Center.y - headSection.Radius / 3);
		Point rightEyeCenter = new Point(headSection.Center.x + headSection.Radius / 3, leftEyeCenter.y);
		Circle circle1_LeftEye = new Circle(leftEyeCenter, 5, 1, null, Color.BLACK);
		Circle circle1_RightEye = new Circle(rightEyeCenter, 5, 1, null, Color.BLACK);

		// Mouth
		Point mouthCenter = new Point(headSection.Center.x, headSection.Center.y + headSection.Radius / 3);

		// Middle Section
		Circle middleSection = new Circle(
				new Point(headSection.Center.x, headSection.Center.y + headSection.Radius + 50), 50, 2, Color.BLACK,
				null);

		// Bottom Section
		Circle bottomSection = new Circle(
				new Point(middleSection.Center.x, middleSection.Center.y + middleSection.Radius + 80), 80, 2,
				Color.BLACK, null);

		// Draw the snowman
		headSection.draw(canvas);
		middleSection.draw(canvas);
		bottomSection.draw(canvas);
		circle1_LeftEye.draw(canvas);
		circle1_RightEye.draw(canvas);
		canvas.drawArc(mouthCenter.x - 10, mouthCenter.y - 5, 20, 10, 0, -180);		
	}

	public class Circle {
		public Point Center;
		public int Radius;
		public int Stroke;
		private Color BorderColor = null;
		private Color FillColor = null;
		public int CutOutPosition = -999;

		// Constructors
		public Circle(Point centerPoint, int radius, int stroke, Color borderColor, Color fillColor) {
			Center = centerPoint;
			Radius = radius;
			if (borderColor != null) {
				BorderColor = borderColor;
			}

			if (fillColor != null) {
				FillColor = fillColor;
			}
			Stroke = stroke;
		}

		/**
		 * Set the angle at which the circle will be cut out.
		 */
		public void setCutOutPosition(int angle) {
			CutOutPosition = angle;
		}

		/**
		 * Set border color
		 */
		public void setBorderColor(Color color) {
			BorderColor = color;
		}

		/**
		 * Get border color
		 */
		public Color getBorderColor() {
			return BorderColor;
		}

		/**
		 * Set fill color
		 */
		public void setFillColor(Color color) {
			FillColor = color;
		}

		/**
		 * Get fill color
		 */
		public Color getFillColor() {
			return FillColor;
		}

		/**
		 * Draw the circle to the canvas.
		 */
		public void draw(Graphics canvas) {
			// Set stroke
			Graphics2D canvas2D = (Graphics2D) canvas;
			canvas2D.setStroke(new BasicStroke(Stroke));

			// Set color
			canvas.setColor(BorderColor);

			// Draw
			if (CutOutPosition != -999) {
				// Draw Border
				if (BorderColor != null) {
					canvas.drawArc(Center.x - Radius, Center.y - Radius, (Radius * 2), (Radius * 2),
							(CutOutPosition + Stroke), 360 - Stroke * 2);
				}

				// Fill color
				if (FillColor != null) {
					canvas.setColor(FillColor);
					canvas.fillArc(Center.x - Radius, Center.y - Radius, (Radius * 2), (Radius * 2),
							(CutOutPosition + Stroke), 360 - Stroke * 2);
				}

			} else {
				// Draw Border
				if (BorderColor != null) {
					canvas.drawOval(Center.x - Radius, Center.y - Radius, Radius * 2, Radius * 2);
				}

				// Fill color
				if (FillColor != null) {
					canvas.setColor(FillColor);
					canvas.fillArc(Center.x - Radius, Center.y - Radius, Radius * 2, Radius * 2, 0, 360);
				}
			}
		}
	}
}
