package com.bjut.model;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Line extends Element {

	@Override
	public void draw(Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
		canvas.drawLine(pointStart.x, pointStart.y, pointEnd.x, pointEnd.y,
				paint);
	}





	
	public boolean isSelected(Point p) {
		// TODO Auto-generated method stub
		
        
		Point pStart = new Point(pointStart.x,pointStart.y);
		Point pEnd = new Point(pointEnd.x,pointEnd.y);
      
		int maxX,maxY,minX,minY;
		
		if(pStart.x>pEnd.x){
			maxX = pStart.x;
			minX = pEnd.x;
		}else{
			maxX = pEnd.x;
			minX = pStart.x;
		}
		
		if(pStart.y>pEnd.y){
			maxY = pStart.y;
			minY = pEnd.y;
		}else{
			maxY = pEnd.y;
			minY = pStart.y;
		}
		
        if (!(p.x > minX-5 && p.x<maxX+5 && p.y > minY-5 && p.y < maxY+5)){
			
			return false;
		}else{
		
			
			double k = (double)(pEnd.y - pStart.y) / (pEnd.x - pStart.x);
			double b1 = (double)(pStart.y - k*pStart.x);
			double result = p.x*k + b1;
			
			if (result - 10 <= p.y&&p.y <= result + 10){
				return true;
			}
			else{
				return false;
			}
		}
	}
	//}

	@Override
	void unSelected() {
		// TODO Auto-generated method stub

	}



	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Line line = (Line) super.clone();
		line.setPointStart((Point)getPointStart().clone());
		line.setPointEnd((Point)getPointEnd().clone());
		return line;
	}


    

}
