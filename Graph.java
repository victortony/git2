package com.bjut.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class Graph {
	
	
	private static Graph graph = null;
	private List<Element> elems = new ArrayList<Element>();
	private Stack<Element> backStack = new Stack<Element>();
	
	public static Graph getInstance() {
		if ( graph == null ) {
			graph = new Graph();
		}
		return graph;
	}
	
	private Graph() {}

	public synchronized void add(Element e) {
		this.elems.add(e);
	}
	
	public synchronized void remove(Element e) {
		this.elems.remove(e);
	}

	public int getSize() {
		return this.elems.size();
	}

	public Element getElem(int idx) {
		return this.elems.get(idx);
	}

	public void draw(Canvas canvas, Paint paint) {
		int i=0;
		for (Element e : elems) {
			e.draw(canvas, paint);
		    Log.i("clone",e.pointStart.toString()+(i++));
		}
	}

	public synchronized void clear() {
		this.elems.clear();
	}

	public int selectedWho(Point p) {

		for (int i = elems.size() - 1; i >= 0; i--)
			if (elems.get(i).isSelected(p)) {
				return i;
			}
		return -1;
	}

	public void move(int index, int deltaX1, int deltaY1, int deltaX2,
			int deltaY2) {

		this.elems.get(index).move(deltaX1, deltaY1, deltaX2, deltaY2);

	}
	//³·Ïû
	public synchronized void undo(){
		if(!this.elems.isEmpty()){
		   Element lastElm = this.elems.get(elems.size()-1); 
		   elems.remove(elems.size()-1);
		   this.backStack.push(lastElm);
		}
	}
	
	//·´³·Ïû
	public synchronized void redo(){
        if(!this.backStack.isEmpty()){
        	this.elems.add(this.backStack.pop());
        }
	}
	
	public void del(int index){
		if(index<this.elems.size()){
			this.elems.remove(index);
		}
	}
	
	public void copy(int index) throws CloneNotSupportedException{
		if(index<this.elems.size()){
			Element elemCopy = (Element) graph.getElem(index).clone();
		    this.elems.add(elemCopy);
		}
	}
}
