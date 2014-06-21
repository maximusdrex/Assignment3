
import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Classes {
public int loop = 1;
	public void run() {
		GRect rect = new GRect(100, 100, 100, 100);
		add(rect);
		while(loop < 0) {
			rect.move(3, 2);
			add(rect);
		}
		
	}
}
