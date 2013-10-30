![alt GraphEditor logo](/images/logo_transparent.png "GraphEditor")

GraphEditor
===========

![alt GraphEditor user interface screen](/images/ui_screen_00.png "GraphEditor User Interface")

Useful links
------------

For more detail information how to use application see:

[GraphEditor project home page](http://graph-editor.xesenix.pl/)

For information about project development strategies check:

[github wiki](https://github.com/Xesenix/graph-editor/wiki)

For builded project documentation check:

[project docs](http://graph-editor.xesenix.pl/docs)

For builded example check sourceforge:

[download windows installer](https://sourceforge.net/projects/graph-editor/)

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

