package org.sep.framework;

import org.sep.drawable.Drawable;

public abstract class GameObject implements Updateable, Drawable {
    
    public abstract void initialize();
    
    public abstract void dispose();
}
