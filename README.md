# Pre-Conf
Configuring a Java application has many quirks. This module, acknowledges that there cannot be a solution to fit all, so it just provides a facility to resolve an external configuration directory for an application, in a versatile way, and validate that declared required configuration files exist there. How these configuration files are consumed, is for the application to code.

## So what do I get
By using the module, your application (that wants to use an external configuration directory), can resolve which is the directory as follows:
1. Before bootstraping **Pre-Conf** the coder will have to provide the application prefix (*{appPrefix}*). This will be used for the configuration resolution.
2. If the coder provided the system property *{appPrefix}.conf.dir* then this directory will be used:

      -DmyApp.conf.dir=/var/conf/myapp

3. If the system property *{appPrefix|uppercase}_CONF_DIR* is set, then this directory will be used:

    export MYAPP_CONF_DIR=/var/conf/myapp

4. If no system or environment property is set, then a default directory will be selected:
    
    /etc/myapp
    
After the directory is resolved, it needs to be validated.

1. It should be validated that the configuration directory exists.
2. All the designated configuration resources (files) should be included in the resolved directory.
3. If any of the above fail, a runtime exception is thrown and it is up the application to handle it. One could implement a default configuration fallback while another might choose to block any access. 



    