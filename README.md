# android-iconizer
Java tool to create resized copies of an Image to use on android resources.

## How it works

- Receives any valid image file as an input.
- Creates android drawable folder structure and puts each corresponding image on it's place.

## Usage

- gui:

    - Open ```bin/``` folder.
        - **Unix**: Execute ```android-iconizer-gui.sh```
        - **Windows**: Execute ```android-iconizer-gui.cmd```

- cli:

    - ```-i(--input)```*    Absolute path of icon to resize.
    - ```-o(--output)```    Absolute to save the resized images to.
    - ```-n(--name)```      Output filename of the icon.
    - ```-t(--type)[contextual,actionbar,notification]``` Type of icon that specifies the resolution used to resize the icon (default is contextual).

    ```
    java -jar android-iconizer.jar -i "C:/image.png" -o "C:/output/" -name "my-awesome-icon" -t contextual
    ```

\* *required*

## Build

To build use the following maven cli command:

```
mvn clean install assembly:single
```

It will generate two files:

- **android-iconizer-{version}.jar**: No dependency binary. (won't work unless ou set the classpath with all dependencies manually)
- **android-iconizer.jar**: Binary with library dependencies embedded. This is the actual executable.

## Dependencies

Special thanks to the creators of these libraries:

- [imgscalr](https://github.com/thebuzzmedia/imgscalr) by [thebuzzmedia](https://github.com/thebuzzmedia/)
- [args4j](https://github.com/kohsuke/args4j) by [kohsuke](https://github.com/kohsuke/args4j)

