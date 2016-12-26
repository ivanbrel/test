Example: Embedded Jetty w/ JSP Support 
======================================

This is a maven project, to build it:

    $ mvn clean package

To run the example `Main`:

    $ mvn exec:exec

Open your web browser to:

    http://localhost:8080/  

To stop Jetty:

  use <kbd>CTRL</kbd>+<kbd>C</kbd>


Code Of Interest
----------------

See [Main](src/main/java/by/ibrel/testapp/Main.java)

**Use JavaC, Not Eclipse JDT**

By default, the JSP implementation will use an internal eclipse JDT compiler,
Using this system property will disable that behavior and instead use the JDK
JavaC built-in compiler.

``` java
// Set JSP to use Standard JavaC always
System.setProperty("org.apache.jasper.compiler.disablejsr199","false");
```

**Set a Servlet Temp Directory**

It is important for JSP to define a temp directory suitable for managing itself.
Such a directory will be used for converting the JSP source into a java file and
then compiling it into a class.  A sub directory in this temp directory will be
automatically added by the JSP implementation for loading the compiled JSP classes.

```java
context.setAttribute("javax.servlet.context.tempdir",scratchDir);
```

**Set a non-System Classloader**

The JSP implementation will refuse to the System Classloader (per JSTL + JSP spec),
this will wrap the system classloader in a simple URLClassLoader suitable
for use by the JSP implementation.

```java
// Set Classloader of Context to be sane (needed for JSTL)
// JSP requires a non-System classloader, this simply wraps the
// embedded System classloader in a way that makes it suitable
// for JSP to use
ClassLoader jspClassLoader = new URLClassLoader(new URL[0], this.getClass().getClassLoader());
context.setClassLoader(jspClassLoader);
```

**Jsp Servlet must be named `"jsp"`**

The JspServlet must be named "jsp" (per JSP spec).

```java
// Add JSP Servlet (must be named "jsp")
ServletHolder holderJsp = new ServletHolder("jsp",JspServlet.class);
holderJsp.setInitOrder(0);
```

**Default Servlet must exist**

The JSP implementation relies on various Servlet Spec requirements,
but mainly the fact that a "default" named servlet must exist.

```java
// Add Default Servlet (must be named "default")
ServletHolder holderDefault = new ServletHolder("default",DefaultServlet.class);
holderDefault.setInitParameter("resourceBase",baseUri.toASCIIString());
holderDefault.setInitParameter("dirAllowed","true");
context.addServlet(holderDefault,"/");
```

