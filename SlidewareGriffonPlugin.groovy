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

/**
 * @author Andres Almiray
 */
class SlidewareGriffonPlugin {
    // the plugin version
    def version = '0.6'
    // the version or versions of Griffon the plugin is designed for
    def griffonVersion = '0.9.5 > *'
    // resources that are included in plugin packaging
    def pluginIncludes = []
    // the plugin license
    def license = 'Apache Software License 2.0'
    // Toolkit compatibility. No value means compatible with all
    // Valid values are: swing, javafx, swt, pivot, gtk
    def toolkits = ['swing']
    // Platform compatibility. No value means compatible with all
    // Valid values are:
    // linux, linux64, windows, windows64, macosx, macosx64, solaris
    List platforms = []

    // the other plugins this plugin depends on
    def dependsOn = [
                     'transitions'    : '0.6',
                     'css-builder'    : '0.9',
                     'jide-builder'   : '0.7',
                     'glazedlists'    : '0.9',
                     'jbusycomponent' : '0.5.3',
                     'i18n-support'   : '0.2',
                     'lookandfeel'    : '0.6',
                     'syntaxtext'     : '0.2'
    ]

    List authors = [
            [
                    name: 'Andres Almiray',
                    email: 'aalmiray@yahoo.com'
            ],
            [
                    name: 'Alexander Klein',
                    email: 'info@aklein.org'
            ]
    ]
    // URL where documentation can be found
    String documentation = ''
    // URL where source can be found
    String source = 'https://github.com/griffon/griffon-slideware-plugin'
    def title = 'Griffon based slideware'
    // accepts Markdown syntax. See http://daringfireball.net/projects/markdown/ for details
    def description = '''
Griffon based slideware
'''

}
