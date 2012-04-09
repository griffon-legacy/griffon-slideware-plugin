/*
 * Copyright 2009-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package griffon.plugins.slideware;

import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JPanel;
import com.bric.image.transition.Transition2D;

import groovy.lang.*;
import griffon.swing.SwingUtils;
import org.codehaus.groovy.runtime.InvokerHelper;

/**
 * @author Andres Almiray
 */
public class Slide extends JPanel implements GroovyObject {
    // never persist the MetaClass
    private transient MetaClass metaClass;

    private JPanel _content;
    private Transition2D transition;
    private String title;
    private String master;
    private JComponent header;
    private JComponent footer;
    private Closure snapshot;
    private Closure backgroundPainter;
    private List<Closure> slideActions;

    public Slide() {
        this.metaClass = InvokerHelper.getMetaClass(this.getClass());
        super.setLayout(new BorderLayout());
        _content = new JPanel();
        _content.setOpaque(false);
        super.add(_content, BorderLayout.CENTER);
    }

    public Transition2D getTransition() {
        return transition;
    }

    public String getTitle() {
        return title;
    }

    public String getMaster() {
        return master;
    }

    public JComponent getHeader() {
        return header;
    }

    public JComponent getFooter() {
        return footer;
    }

    public List<Closure> getSlideActions() {
        return slideActions;
    }

    public void setTransition(Transition2D transition) {
        this.transition = transition;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public void setHeader(JComponent header) {
        this.header = header;
        if(header != null) {
            header.setOpaque(false);
            super.add(header, BorderLayout.NORTH);
        }
    }
 
    public void setFooter(JComponent footer) {
        this.footer = footer;
        if(footer != null) {
            footer.setOpaque(false);
            super.add(footer, BorderLayout.SOUTH);
        }
    }

    public void setSlideActions(List<Closure> actions) {
        this.slideActions = actions;
    }

    public void setLayout(LayoutManager layout) {
        // guard is required for constructor
        if(_content != null) _content.setLayout(layout);
    }

    public void setSnapshot(Closure snapshot) {
        this.snapshot = snapshot;
        if(snapshot != null) {
            snapshot.setResolveStrategy(Closure.DELEGATE_FIRST);
            snapshot.setDelegate(this);
        }
    }

    public void setBackgroundPainter(Closure backgroundPainter) {
        this.backgroundPainter = backgroundPainter;
        if(backgroundPainter != null) {
            backgroundPainter.setResolveStrategy(Closure.DELEGATE_FIRST);
            backgroundPainter.setDelegate(this);
        }
    }

    public Component add(Component component) {
        return _content.add(component);
    }

    public void add(Component component, Object constraints) {
        _content.add(component, constraints);
    }

    public Image[] takeSnapshot() {
        if(snapshot != null) {
            return (Image[]) snapshot.call();
        }
        return new Image[]{ SwingUtils.takeSnapshot(this) };
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(backgroundPainter != null) {
            backgroundPainter.call(new Object[]{this, g});
        }
    }

    public Object getProperty(String property) {
        try {
            return getMetaClass().getProperty(this, property);
        } catch(MissingPropertyException e) {
            return this.getClientProperty(property);
        }
    }

    public void setProperty(String property, Object newValue) {
        try {
            getMetaClass().setProperty(this, property, newValue);
        } catch(MissingPropertyException e) {
            this.putClientProperty(property, newValue);
        }
    }

    public Object invokeMethod(String name, Object args) {
        try {
            return getMetaClass().invokeMethod(this, name, args);
        } catch(MissingMethodException e) {
            Object clos = getProperty(name);
            if(clos instanceof Closure) {
                return InvokerHelper.invokeClosure(clos, args);
            } else {
                throw e;
            }
        }
    }

    public MetaClass getMetaClass() {
        if (metaClass == null) {
            metaClass = InvokerHelper.getMetaClass(getClass());
        }
        return metaClass;
    }

    public void setMetaClass(MetaClass metaClass) {
        this.metaClass = metaClass;
    }
}
