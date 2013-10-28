![alt GraphEditor logo](/images/logo_transparent.png "GraphEditor")

GraphEditor
===========

![alt GraphEditor user interface screen](/images/ui_screen_00.png "GraphEditor User Interface")

[GraphEditor project page](http://graph-editor.xesenix.pl/)

[See wiki](https://github.com/Xesenix/graph-editor/wiki/GraphEditor-Maven)

How to build
------------

One time run for system setup of javafx runtimes

	maven javafx:fix-classpath

For building project to a:

- jar executable

		maven clean javafx:jar

- native executable:

		maven clean javafx:native

For running builded application

	maven javafx:run

More info about javafx-maven-plugin
-----------------------------------

Using javaFX requires http://zenjava.com/javafx/maven/fix-classpath.html

For more info on building project see:

http://zenjava.com/javafx/maven/index.html

Maven goals:

http://zenjava.com/javafx/maven/plugin-info.html

