**Localization**

There are several text resources to support localizations for different countries and languages. This resources are placed into  [values folder](https://code.google.com/p/orion-viewer/source/browse/#git%2Forion-viewer%2Fres%2Fvalues) and splited in several categories:
  * menu.xml - menu resources
  * actions.xml - action resources (tap zones action description)
  * help.xml - help resources
  * pref.xml - preference resources
  * langs.xml - list of supported languages
  * strings.xml - other resources


Each resource file is an xml file with a list of **string** tags that contains id of resource `resourceId` (which should stay unchanged) and localized text (in example below it's english localization):
> `<string name="resourceId">Text for translation</string>`

For localization to any language:
  1. Download preferred resource files
  1. Translate text inside **string** tags (_"Text for translation"_ in example above) to choosen language
  1. Send it back to me or attach it to new issue in [Issues](http://code.google.com/p/orion-viewer/issues/list) tab

[English](http://code.google.com/p/orion-viewer/source/browse/#git%2Forion-viewer%2Fres%2Fvalues), [Russian](http://code.google.com/p/orion-viewer/source/browse/#git%2Forion-viewer%2Fres%2Fvalues-ru), [Italian](http://code.google.com/p/orion-viewer/source/browse/#git%2Forion-viewer%2Fres%2Fvalues-it), [Chinese](http://code.google.com/p/orion-viewer/source/browse/#git%2Forion-viewer%2Fres%2Fvalues-zh), [Spanish](http://code.google.com/p/orion-viewer/source/browse/#git%2Forion-viewer%2Fres%2Fvalues-es) and [Hebrew](http://code.google.com/p/orion-viewer/source/browse/#git%2Forion-viewer%2Fres%2Fvalues-iw) translation examples.

Please use [English](http://code.google.com/p/orion-viewer/source/browse/#git%2Forion-viewer%2Fres%2Fvalues) or [Russian](http://code.google.com/p/orion-viewer/source/browse/#git%2Forion-viewer%2Fres%2Fvalues-ru) resources as reference one for translations!