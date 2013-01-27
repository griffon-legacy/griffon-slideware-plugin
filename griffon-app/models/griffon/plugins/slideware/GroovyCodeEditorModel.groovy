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

package griffon.plugins.slideware

import org.fife.ui.autocomplete.BasicCompletion
import org.fife.ui.autocomplete.CompletionProvider
import org.fife.ui.autocomplete.DefaultCompletionProvider

/**
 * @author Andres Almiray
 */
class GroovyCodeEditorModel {
    @Bindable String code = ''
    @Bindable boolean editable = false

    def document
    Map styles = [:]

    final CompletionProvider codeCompletionProvider = createCompletionProvider()

    private CompletionProvider createCompletionProvider() {
        // A DefaultCompletionProvider is the simplest concrete implementation
        // of CompletionProvider.  This provider has no understanding of
        // language semantics. It simply checks the text entered up to the
        // caret position for a match against known completions. This is all
        // that is needed in the majority of cases.
        DefaultCompletionProvider provider = new DefaultCompletionProvider()

        // Add completions for all Groovy keywords.  A BasicCompletion is just
        // a straightforward word completion.
        keyword(provider, 'abstract')
        keyword(provider, 'assert')
        keyword(provider, 'as')
        keyword(provider, 'break')
        keyword(provider, 'case')
        keyword(provider, 'catch')
        keyword(provider, 'class')
        keyword(provider, 'const')
        keyword(provider, 'continue')
        keyword(provider, 'default')
        keyword(provider, 'do')
        keyword(provider, 'else')
        keyword(provider, 'enum')
        keyword(provider, 'extends')
        keyword(provider, 'final')
        keyword(provider, 'finally')
        keyword(provider, 'for')
        keyword(provider, 'goto')
        keyword(provider, 'if')
        keyword(provider, 'implements')
        keyword(provider, 'import')
        keyword(provider, 'in')
        keyword(provider, 'instanceof')
        keyword(provider, 'interface')
        keyword(provider, 'native')
        keyword(provider, 'new')
        keyword(provider, 'package')
        keyword(provider, 'private')
        keyword(provider, 'protected')
        keyword(provider, 'public')
        keyword(provider, 'return')
        keyword(provider, 'static')
        keyword(provider, 'strictfp')
        keyword(provider, 'super')
        keyword(provider, 'switch')
        keyword(provider, 'synchronized')
        keyword(provider, 'this')
        keyword(provider, 'throw')
        keyword(provider, 'throws')
        keyword(provider, 'transient')
        keyword(provider, 'try')
        keyword(provider, 'void')
        keyword(provider, 'volatile')
        keyword(provider, 'while')

        provider
    }

    private static void keyword(CompletionProvider provider, String text) {
        provider.addCompletion(new KeywordCompletion(provider, text))
    }

    static class KeywordCompletion extends BasicCompletion {
        KeywordCompletion(CompletionProvider provider, String text) {
            super(provider, text)
        }

        String getReplacementText() {
            super.getReplacementText() + ' '
        }

        String getInputText() {
            getReplacementText()[0..-2]
        }
    }
}