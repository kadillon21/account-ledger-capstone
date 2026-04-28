package com.pluralsight.util;

public class ColorUtilities {

    // Reset
    public static final String RESET = "\u001B[0m";



    // Text styles
    public static final String BOLD = "\u001B[1m";
    public static final String STRIKETHROUGH = "\u001B[9m";
    public static final String ITALIC = "\u001B[3m";
    public static final String UNDERLINE = "\u001B[4m";
    public static final String REVERSED = "\u001B[7m";

    // Regular colors
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    // Bright colors
    public static final String BRIGHT_BLACK = "\u001B[90m";
    public static final String BRIGHT_RED = "\u001B[91m";
    public static final String BRIGHT_GREEN = "\u001B[92m";
    public static final String BRIGHT_YELLOW = "\u001B[93m";
    public static final String BRIGHT_BLUE = "\u001B[94m";
    public static final String BRIGHT_PURPLE = "\u001B[95m";
    public static final String BRIGHT_CYAN = "\u001B[96m";
    public static final String BRIGHT_WHITE = "\u001B[97m";

    // Common Phrases
    public static final String ERROR = ColorUtilities.BRIGHT_RED + ColorUtilities.BOLD + "ERROR: " + ColorUtilities.RESET;
    private ColorUtilities() {
    }

    // Color Scheme
    // Structure (borders, dividers)
    public static final String BORDER = "\u001B[38;5;75m";

    // Action / highlight (menu keys, prompts)
    public static final String ACCENT = "\u001B[38;5;208m";

    // Success / money in
    public static final String SUCCESS = "\u001B[38;5;114m";

    // Danger / money out / errors
    public static final String DANGER = "\u001B[38;5;203m";

    // Warning / (confirmations)

    public static final String WARNING = ColorUtilities.YELLOW;

    // Muted (helper text, timestamps)
    public static final String MUTED = "\u001B[38;5;243m";

    public static final String ARROW = ColorUtilities.CYAN + "❯ " + ColorUtilities.RESET;
}
