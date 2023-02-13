# vision-java-sdk

The VISION JAVA client library.

## How to use

### Gradle Setting

Add repo setting:

```groovy
repositories {
    maven {
        url  "https://maven.pkg.github.com/vision-consensus/vision-java-sdk"
    }
}
```

Then add `abi` as dependency.

```groovy
dependencies {
    ....

    implementation 'org.vision.visionjsdk:abi:0.5.0'
    implementation 'org.vision.visionjsdk:client:0.5.0'
    implementation 'org.vision.visionjsdk:utils:0.5.0'

    ....
}
```

### Maven Settings

```xml
<dependency>
  <groupId>org.vision.visionjsdk</groupId>
  <artifactId>abi</artifactId>
  <version>0.5.0</version>
  <type>pom</type>
</dependency>
```
```xml
<dependency>
  <groupId>org.vision.visionjsdk</groupId>
  <artifactId>client</artifactId>
  <version>0.5.0</version>
  <type>pom</type>
</dependency>
```
```xml
<dependency>
  <groupId>org.vision.visionjsdk</groupId>
  <artifactId>utils</artifactId>
  <version>0.5.0</version>
  <type>pom</type>
</dependency>
```

### Construct Smart Contract Function Demo 

Refer `demo` project.

```java
/*
import java.math.BigInteger;
import java.util.*;

import FunctionEncoder;
import org.vision.visionjsdk.abi.datatypes.*;
import Bytes10;
import Uint256;
import Uint32;
*/


// Function(name, input, output)
Function function =
        new Function(
                "sam",
                Arrays.asList(
                        new DynamicBytes("dave".getBytes()),
                        new Bool(true),
                        new Address("VBZsQtsKdzFU5fLyHWeduQLHAw6HvSDd7i"),
                        new DynamicArray<>(
                                new Uint(BigInteger.ONE),
                                new Uint(BigInteger.valueOf(2)),
                                new Uint(BigInteger.valueOf(3)))),
                Collections.emptyList());
String encodedHex = FunctionEncoder.encode(function);


```
