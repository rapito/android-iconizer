# android-iconizer
Java tool to create resized copies of an Image to use on android resources.

## How it works

- Receives any valid image file as an input.
- Creates android dpi folder structure and puts each corresponding image in it's place

## Usage

- cli:

    - ```-i(--input)```*    Absolute path of icon to resize.
    - ```-o(--output)```    Absolute to save the resized images to.
    - ```-n(--name)```      Output filename of the icon.
    - ```-t(--type)[contextual,actionbar,notification]``` Type of icon that specifies the resolution used to resize the icon (default is contextual).

    ```
    java -jar android-iconizer.jar -i "C:/image.png" -o "C:/output/" -name "my-awesome-icon" -t contextual
    ```

\* *required*

## Dependencies

Special thanks to the creators of these libraries:

- [imgscalr](https://github.com/thebuzzmedia/imgscalr) by [thebuzzmedia](https://github.com/thebuzzmedia/)
- [args4j](https://github.com/kohsuke/args4j) by [kohsuke](https://github.com/kohsuke/args4j)

