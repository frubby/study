package com.frw.lean.pattern.decorator;

public class Decorator extends Component {

	Component comp;
	public Decorator(Component comp){
		this.comp=comp;
	}
	@Override
	public void operate() {

		 comp.operate();
		
		
	}

}
