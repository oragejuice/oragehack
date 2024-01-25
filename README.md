# oragehack
reinventing the idea of overengineered

## How to build
run `./gradlew oragehack:build`

build should be in `oragehack/build/libs`

## Papers I need to read to help me
- https://code.google.com/archive/p/java-gpu/
- http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.174.8624&rep=rep1&type=pdf

## Credits
made by oragejuce (mostly) with pain and love
Special thanks to:
- Jenni
- 254n_m for github CI stuff
- thank u nuker (for being awesome)
- 0x22 (for keeping me motivated) also 254n_m (helping me with gradle)

# How to install

This assumes you use multimc / prism-launcher

Click `edit instance`

Go to the `versions` tab

Click `add empty`
set `name` to `oragehack` and `uid` to `me.oragejuice.oragehack`

Select `edit` and replace with the Json below

Click `Open libraries` and drag the jar into it

Install forge and make sure it loads **before** oragehack
```
{
    "+tweakers": [
        "me.oragejuice.oragehack.tweak.Tweaker"
    ], 
    "formatVersion": 1,
    "libraries": [
        {
            "name": "net.minecraft:launchwrapper:1.12"
        },
        {
            "name": "org.ow2.asm:asm-all:4.1"
        },
        {
            "name": "me.oragejuice.oragehack:oragehack:1.0",
            "MMC-hint": "local",
            "MMC-filename": "oragehack-1.0.jar"
        }
    ],
    "name": "oragehack",
    "requires": [
        {
            "equals": "1.12",
            "uid": "net.minecraft"
        }
    ],
    "uid": "me.oragejuice.oragehack",
    "version": "1",
    "mainClass": "net.minecraft.launchwrapper.Launch"
}
```


# Contributing

## Formatting
When writing classes and functions they should all follow the format
```java
class aClass {
    public void aFunction() {
        if (something()) {
            //do thing
        }
    }
}
```
take note of the use of Camel case, and the spacing after each call.
the braces should match the indentation of the call.

## Design
Secondly `static` functions should only be written if they are either purely functional (no side effects)
unless if they interface with an inherently stateful system
e.g GL or MC itself. The exception to this is Factories.
Otherwise, if needed then use singletons


