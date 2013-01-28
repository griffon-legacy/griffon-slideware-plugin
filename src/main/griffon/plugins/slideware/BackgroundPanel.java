/*
 * Copyright 2009-2013 the original author or authors.
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

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;

/**
 * A container that draws a solid color or a paint as background
 *
 * @author Andres Almiray
 */
public class BackgroundPanel extends DrawingPanel {
    private Paint backgroundPaint = Color.BLACK;

    public Paint getBackgroundPaint() {
        return backgroundPaint;
    }

    public void setBackgroundPaint(Paint backgroundPaint) {
        this.backgroundPaint = backgroundPaint;
        clearCache();
        repaint();
    }

    protected void paintImage(Graphics2D g, Rectangle bounds) {
        g.clearRect(bounds.x, bounds.y, bounds.width, bounds.height);
        g.setPaint(backgroundPaint);
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
}
