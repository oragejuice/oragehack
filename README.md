# oragehack
### reinventing the idea of (((overe ngineered)))

## how to build
- so basically you need to first build the processor
- then push it to the maven local
- only then can you build the main client :D

## papers i read to help me
- https://code.google.com/archive/p/java-gpu/
- http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.174.8624&rep=rep1&type=pdf
- 
#### kk thanks bai

- made by oragejuce with pain and love
- thank u nuker (for being awesome) and 0x22 (for being mean)

## The Thing
```{
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

Still not sure how this works on vanilla ngl \
I juse use MultiMC