from PIL import Image

def make_pixels_white(input_path, output_path):
    # Bild laden
    img = Image.open(input_path).convert("RGBA")
    pixels = img.load()

    width, height = img.size

    for y in range(height):
        for x in range(width):
            r, g, b, a = pixels[x, y]

            if a != 0:  # Nur wenn Pixel nicht vollständig transparent ist
                pixels[x, y] = (255, 255, 255, a)  # Pures Weiß + ursprünglicher Alpha

    img.save(output_path, "PNG")

# Beispiel
make_pixels_white("Vordergrund.png", "output_white.png")
