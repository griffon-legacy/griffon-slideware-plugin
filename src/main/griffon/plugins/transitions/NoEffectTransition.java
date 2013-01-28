/*
 * Copyright 2012-2013 the original author or authors.
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

package griffon.plugins.transitions;

import java.awt.Dimension;

import com.bric.image.transition.ImageInstruction;
import com.bric.image.transition.Transition2D;
import com.bric.image.transition.Transition2DInstruction;

/**
 * Transition that just draws the new slide.
 *
 * @author Burt Beckwith
 */
public class NoEffectTransition extends Transition2D {
	@Override
    public Transition2DInstruction[] getInstructions(float progress, Dimension size) {
		return new Transition2DInstruction[] { new ImageInstruction(false) };
	}
}