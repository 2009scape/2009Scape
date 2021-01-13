package org.runite.client;

import javax.swing.*;
import java.awt.event.*;

final class MouseListeningClass implements MouseListener, MouseMotionListener, FocusListener
{
    static volatile int anInt1340 = -1;
    public int mouseWheelX;
	  public int mouseWheelY;
	  static float[] aFloatArray1919 = new float[4];
	  static int[] anIntArray1920;
	  static int anInt1921 = 0;
	  static int anInt1923;
	  static int anInt1924 = 0;
	  static int anInt1925 = 0;
	  static int anInt1926;

	public final synchronized void mouseMoved(MouseEvent var1)
	  {
	    try
	    {
	      if (Unsorted.aClass149_4047 != null)
	      {
	        Unsorted.anInt4045 = 0;
	        Class3_Sub21.anInt2493 = var1.getX();
	        anInt1340 = var1.getY();
	      }
	    }
	    catch (RuntimeException var3)
	    {
	      throw ClientErrorException.clientError(var3, "ug.mouseMoved(" + (var1 != null ? "{...}" : "null") + ')');
	    }
	  }
	  
	  static void method2087()
	  {
	    try
	    {
	      Class3_Sub4 var1 = (Class3_Sub4)Class3_Sub13_Sub6.aClass61_3075.method1222();
	      for (; var1 != null; var1 = (Class3_Sub4)Class3_Sub13_Sub6.aClass61_3075.method1221())
	      {
	        if (var1.anInt2259 > 0) {
	          var1.anInt2259 -= 1;
	        }
	        if (var1.anInt2259 != 0)
	        {
	          if (var1.anInt2261 > 0) {
	            var1.anInt2261 -= 1;
	          }
	          if ((var1.anInt2261 == 0) && (1 <= var1.anInt2264) && (1 <= var1.anInt2248) && (102 >= var1.anInt2264) && (var1.anInt2248 <= 102) && ((var1.anInt2265 < 0) || (Unsorted.method590((byte)-34, var1.anInt2265, var1.anInt2262))))
	          {
	            Unsorted.method1048(var1.anInt2265, var1.anInt2264, var1.anInt2250, var1.anInt2256, var1.anInt2248, -65, var1.anInt2262, var1.anInt2263);
	            var1.anInt2261 = -1;
	            if ((var1.anInt2265 == var1.anInt2254) && (var1.anInt2254 == -1)) {
	              var1.unlink();
	            } else if ((var1.anInt2254 == var1.anInt2265) && (var1.anInt2256 == var1.anInt2257) && (var1.anInt2262 == var1.anInt2253)) {
	              var1.unlink();
	            }
	          }
	        }
	        else if ((var1.anInt2254 < 0) || (Unsorted.method590((byte)-66, var1.anInt2254, var1.anInt2253)))
	        {
	          Unsorted.method1048(var1.anInt2254, var1.anInt2264, var1.anInt2250, var1.anInt2257, var1.anInt2248, -71, var1.anInt2253, var1.anInt2263);
	          var1.unlink();
	        }
	      }
	    }
	    catch (RuntimeException var3)
	    {
	      throw ClientErrorException.clientError(var3, "ug.A(" + (byte) -82 + ')');
	    }
	  }

	public final synchronized void focusLost(FocusEvent var1)
	  {
	    try
	    {
	      if (Unsorted.aClass149_4047 != null) {
	        GraphicDefinition.anInt549 = 0;
	      }
	    }
	    catch (RuntimeException var3)
	    {
	      throw ClientErrorException.clientError(var3, "ug.focusLost(" + (var1 != null ? "{...}" : "null") + ')');
	    }
	  }
	  
//	  static void method2089()
//	  {
//	    try
//	    {
//	      Class158_Sub1.aReferenceCache_2982.clearSoftReferences();
//		}
//	    catch (RuntimeException var2)
//	    {
//	      throw ClientErrorException.clientError(var2, "ug.D(" + (byte) 115 + ')');
//	    }
//	  }
	  
	  public final synchronized void mouseDragged(MouseEvent var1)
	  {
	    try
	    {
	      int x = var1.getX();
	      int y = var1.getY();
	      if (SwingUtilities.isMiddleMouseButton(var1))
	      {
	        int accelX = this.mouseWheelX - x;
	        int accelY = this.mouseWheelY - y;
	        this.mouseWheelX = var1.getX();
	        this.mouseWheelY = var1.getY();
	        GraphicDefinition.CAMERA_DIRECTION += accelX * 2;
	        Unsorted.anInt2309 -= (accelY << 1);
	        return;
	      }
	      if (Unsorted.aClass149_4047 != null)
	      {
	        Unsorted.anInt4045 = 0;
	        Class3_Sub21.anInt2493 = var1.getX();
	        anInt1340 = var1.getY();
	      }
	    }
	    catch (RuntimeException var3)
	    {
	      throw ClientErrorException.clientError(var3, "ug.mouseDragged(" + (var1 != null ? "{...}" : "null") + ')');
	    }
	  }
	  
	  public final synchronized void mouseReleased(MouseEvent var1)
	  {
	    try
	    {
	      if (Unsorted.aClass149_4047 != null)
	      {
	        Unsorted.anInt4045 = 0;
	        GraphicDefinition.anInt549 = 0;
		  }
	      if (var1.isPopupTrigger()) {
	        var1.consume();
	      }
	    }
	    catch (RuntimeException var3)
	    {
	      throw ClientErrorException.clientError(var3, "ug.mouseReleased(" + (var1 != null ? "{...}" : "null") + ')');
	    }
	  }
	  
	  public final void mouseClicked(MouseEvent var1)
	  {
	    try
	    {
	      if (var1.isPopupTrigger()) {
	        var1.consume();
	      }
	    }
	    catch (RuntimeException var3)
	    {
	      throw ClientErrorException.clientError(var3, "ug.mouseClicked(" + (var1 != null ? "{...}" : "null") + ')');
	    }
	  }
	  
	  public final void focusGained(FocusEvent var1) {}
	  
	  static void method2090()
	  {
	    try
	    {
	      if (Unsorted.aClass149_4047 != null)
	      {
	       MouseListeningClass var1 = Unsorted.aClass149_4047;
	        synchronized (var1)
	        {
	          Unsorted.aClass149_4047 = null;
	        }
	      }
		}
	    catch (RuntimeException var4)
	    {
	      throw ClientErrorException.clientError(var4, "ug.F(" + 8 + ')');
	    }
	  }
	  
	  public final synchronized void mousePressed(MouseEvent var1)
	  {
	    try
	    {
	      if (SwingUtilities.isMiddleMouseButton(var1))
	      {
	        this.mouseWheelX = var1.getX();
	        this.mouseWheelY = var1.getY();
	        return;
	      }
	      if (Unsorted.aClass149_4047 != null)
	      {
	        Unsorted.anInt4045 = 0;
	        RenderAnimationDefinition.anInt362 = var1.getX();
	        Class3_Sub13_Sub32.anInt3389 = var1.getY();
	        Class140_Sub6.aLong2926 = TimeUtils.time();
	        if (var1.getButton() == MouseEvent.BUTTON3) // SwingUtilities.isRightMouseButton(var1)
	        {
				Class140_Sub3.anInt2743 = 2;
				GraphicDefinition.anInt549 = 2;
	        }
	        else if (SwingUtilities.isLeftMouseButton(var1))
	        {
				Class140_Sub3.anInt2743 = 1;
				GraphicDefinition.anInt549 = 1;
			}
		  }
	      if (var1.isPopupTrigger()) {
	        var1.consume();
	      }
	    }
	    catch (RuntimeException var3)
	    {
	      throw ClientErrorException.clientError(var3, "ug.mousePressed(" + (var1 != null ? "{...}" : "null") + ')');
	    }
	  }
	  
	  public final synchronized void mouseExited(MouseEvent var1)
	  {
	    try
	    {
	      if (Unsorted.aClass149_4047 != null)
	      {
	        Unsorted.anInt4045 = 0;
	        Class3_Sub21.anInt2493 = -1;
	        anInt1340 = -1;
	      }
	    }
	    catch (RuntimeException var3)
	    {
	      throw ClientErrorException.clientError(var3, "ug.mouseExited(" + (var1 != null ? "{...}" : "null") + ')');
	    }
	  }
	  
	  static void method2091(int var0)
	  {
	    try
	    {
	      if ((GameObject.anIntArray1838 == null) || (GameObject.anIntArray1838.length < var0)) {
	        GameObject.anIntArray1838 = new int[var0];
	      }
		}
	    catch (RuntimeException var3)
	    {
	      throw ClientErrorException.clientError(var3, "ug.E(" + var0 + ',' + 4 + ')');
	    }
	  }
	  
	  public final synchronized void mouseEntered(MouseEvent var1)
	  {
	    try
	    {
	      if (Unsorted.aClass149_4047 != null)
	      {
	        Unsorted.anInt4045 = 0;
	        Class3_Sub21.anInt2493 = var1.getX();
	        anInt1340 = var1.getY();
	      }
	    }
	    catch (RuntimeException var3)
	    {
	      throw ClientErrorException.clientError(var3, "ug.mouseEntered(" + (var1 != null ? "{...}" : "null") + ')');
	    }
	  }
	  
	  static void method2092(int var0)
	  {
	    try
	    {
	      InterfaceWidget var2 = Class3_Sub24_Sub3.getWidget(9, var0);
	      var2.a();
	    }
	    catch (RuntimeException var3)
	    {
	      throw ClientErrorException.clientError(var3, "ug.B(" + var0 + ',' + (byte) -47 + ')');
	    }
	  }
	}
