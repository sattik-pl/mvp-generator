# mvp-generator
Model-View-Presenter Template Generator for Java projects in Eclipse IDE
------------------------------------------------------------------------

If you want to build a GUI app that uses Swing [JFrame] library, the MVP Template Generator will generate for your project basic source and test folders together with template classes following the Model-View-Presenter architecture to give you a quick start in building your app. This way, every time you set up a new project, you will not have to start from scratch.

Here's what you need:
---------------------
1. Java 8+
2. Eclipse IDE
3. The following folders and files from MVP Generator:
>+ [dat] 
>+ [properties]
>+ *"MVP Generator.bat"*
>+ *"MVP Generator.jar"*

**Important note:**
**So far, MVP Generator was tested in Windows only! Versions for Linux or iOS will require a few tweaks in the code and a `.sh` file.**

Here's how it works:
--------------------
1. Open Eclipse IDE and create a new Java project (*File/New/Java Project*)
2. Open *"project.properties"* file from [properties] and specify:
>+ path to the project
>+ package name
>+ name of the main class (that will start the app, default: *MyApp*)
3. Run *"MVP Generator.bat"*
4. Refresh your project in Eclipse IDE (*File/Refresh* or F5)

Here's what you'll get:
-----------------------
**A working template app** (a window with *Menu/About* feature implemented) that you can run right after it is generated, and build on according to your ingenuity. The app will have the following **folder structure with template Model-View-Presenter and Util classes**:
+ src
    >+ [package].app.**Contract.java**
    >+ [package].app.**MyApp.java**
    
    >+ [package].model.**Model.java**
    
    >+ [package].view.**View.java**
    >+ [package].view.**UIWindow.java**
    
    >+ [package].presenter.**Presenter.java**
    
    >+ [package].utils.**Fonts.java**
    >+ [package].utils.**IOUtils.java**
    >+ [package].utils.**MyStrings.java**
    >+ [package].utils.**Utils.java**
+ test
    >+ [package].app
    >+ [package].model
    >+ [package].presenter

Troubleshooting:
----------------
+ MVP Generator was tested in Windows only!
+ Depending on the terminal (command line) that will be used to run the `.bat` file, it might be necessary to add the following line (after changing the path in quotation marks accordingly, of course):

    `cd /d "d:\path_to_the_generator's_folder\"`
