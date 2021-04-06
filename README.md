# vision-java-sdk

The VISION JAVA client library.

## How to use

### Gradle Setting

Add repo setting:

```groovy
repositories {
    maven {
        url  "xxxxxx"
    }
}
```

Then add `abi` as dependency.

```groovy
dependencies {
    ....

    implementation 'org.vision.visionjsdk:abi:0.1.1'
    implementation 'org.vision.visionjsdk:client:0.1.1'

    ....
}
```

### Maven Settings

```xml
<dependency>
  <groupId>org.vision.visionjsdk</groupId>
  <artifactId>abi</artifactId>
  <version>0.1.1</version>
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
