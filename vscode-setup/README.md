# Use Visual Studio Code as IDE

First, install [VS Code](https://code.visualstudio.com).

Then, install the required extensions to develop Java:

- Install [Java Extension Pack](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)
- Install [Spring Boot support](https://marketplace.visualstudio.com/items?itemName=Pivotal.vscode-spring-boot)

Install required extensions to edit XML files:

- Install [DotJoshJohnson XML Tools](https://marketplace.visualstudio.com/items?itemName=DotJoshJohnson.xml) to enable XPath / XQuery support.
- Install [Qub XML Tools](https://marketplace.visualstudio.com/items?itemName=qub.qub-xml-vscode) to enable tag auto-closing.

Finally, Install [XML Language Support](https://marketplace.visualstudio.com/items?itemName=IBM.XMLLanguageSupport) to enable XSD auto-complete / validation.

This last extension needs a bit of setup to work. Namely, the `JAVA_HOME` environment variable needs to be set properly.

On MacOS this environment variable is not set. You will need to print the Java Home of your current Java installation with:

```
$ /usr/libexec/java_home -v 1.8
/Library/Java/JavaVirtualMachines/jdk1.8.0_172.jdk/Contents/Home
```

Then, open `$HOME/.vscode/settings.json` and add the path to your Java Home in front of the key `xmlLang.javaHome`:

```js
{
  "xmlLang.javaHome": "/Library/Java/JavaVirtualMachines/jdk1.8.0_172.jdk/Contents/Home"
}
```

Restart Visual Studio Code.

## References

- [Java Support in VS Code](https://code.visualstudio.com/docs/languages/java)
