package com.base;

public class AsciiTable {
	/// <summary>
	/// Code of characters
	/// </summary>
	public static final byte[] CODES = { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C,
			0x0D, 0x0E, 0x0F, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17, 0x18, 0x19, 0x1A, 0x1B, 0x1C, 0x1D, 0x1E,
			0x1F, 0x20, 0x21, 0x22, 0x23, 0x24, 0x25, 0x26, 0x27, 0x28, 0x29, 0x2A, 0x2B, 0x2C, 0x2D, 0x2E, 0x2F, 0x30,
			0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39, 0x3A, 0x3B, 0x3C, 0x3D, 0x3E, 0x3F, 0x40, 0x41, 0x42,
			0x43, 0x44, 0x45, 0x46, 0x47, 0x48, 0x49, 0x4A, 0x4B, 0x4C, 0x4D, 0x4E, 0x4F, 0x50, 0x51, 0x52, 0x53, 0x54,
			0x55, 0x56, 0x57, 0x58, 0x59, 0x5A, 0x5B, 0x5C, 0x5D, 0x5E, 0x5F, 0x60, 0x61, 0x62, 0x63, 0x64, 0x65, 0x66,
			0x67, 0x68, 0x69, 0x6A, 0x6B, 0x6C, 0x6D, 0x6E, 0x6F, 0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78,
			0x79, 0x7A, 0x7B, 0x7C, 0x7D, 0x7E, 0x7F };

	/// <summary>
	/// 0. Nul
	/// </summary>
	public static final byte NUL = 0;

	/// <summary>
	/// 1. Start of header
	/// </summary>
	public static final byte SOH = 1;

	/// <summary>
	/// 2. Start of text
	/// </summary>
	public static final byte STX = 2;

	/// <summary>
	/// 3. End of text
	/// </summary>
	public static final byte ETX = 3;

	/// <summary>
	/// 4. End of transmission
	/// </summary>
	public static final byte EOT = 4;

	/// <summary>
	/// 5. Enquiry
	/// </summary>
	public static final byte ENQ = 5;

	/// <summary>
	/// 6. Acknowledgement
	/// </summary>
	public static final byte ACK = 6;

	/// <summary>
	/// 7. Bell
	/// </summary>
	public static final byte BEL = 7;

	/// <summary>
	/// 8. Backspace
	/// </summary>
	public static final byte BS = 8;

	/// <summary>
	/// 9. Horizontal tab
	/// </summary>
	public static final byte TAB = 9;

	/// <summary>
	/// 10. Lin feed
	/// </summary>
	public static final byte LF = 10;

	/// <summary>
	/// 11. Vertical tab
	/// </summary>
	public static final byte VT = 11;

	/// <summary>
	/// 12. Form feed
	/// </summary>
	public static final byte FF = 12;

	/// <summary>
	/// 13. Carriage return
	/// </summary>
	public static final byte CR = 13;

	/// <summary>
	/// 14. Shift out
	/// </summary>
	public static final byte SO = 14;

	/// <summary>
	/// 15. Shift in
	/// </summary>
	public static final byte SI = 15;

	/// <summary>
	/// 16. Data link escape
	/// </summary>
	public static final byte DLE = 16;

	/// <summary>
	/// 17. Device control 1
	/// </summary>
	public static final byte DC1 = 17;

	/// <summary>
	/// 18. Device control 2
	/// </summary>
	public static final byte DC2 = 18;

	/// <summary>
	/// 19. Device control 3
	/// </summary>
	public static final byte DC3 = 19;

	/// <summary>
	/// 20. Device control 4
	/// </summary>
	public static final byte DC4 = 20;

	/// <summary>
	/// 21. NAK Negative-acknowledge
	/// </summary>
	public static final byte NAK = 21;

	/// <summary>
	/// 22. Synchronous idle
	/// </summary>
	public static final byte SYN = 22;

	/// <summary>
	/// 23. End of trans. block
	/// </summary>
	public static final byte ETB = 23;

	/// <summary>
	/// 24. Cancel
	/// </summary>
	public static final byte CAN = 24;

	/// <summary>
	/// 25. End of medium
	/// </summary>
	public static final byte EM = 25;

	/// <summary>
	/// 26. Substitute
	/// </summary>
	public static final byte SUB = 26;

	/// <summary>
	/// 27. Escape
	/// </summary>
	public static final byte ESC = 27;

	/// <summary>
	/// 28. File separator
	/// </summary>
	public static final byte FS = 28;

	/// <summary>
	/// 29. Group separator
	/// </summary>
	public static final byte GS = 29;

	/// <summary>
	/// 30. Record separator
	/// </summary>
	public static final byte RS = 30;

	/// <summary>
	/// 31. Unit separator
	/// </summary>
	public static final byte US = 31;

	/// <summary>
	/// 32. Space
	/// </summary>
	public static final byte SPACE = 32;

	/// <summary>
	/// 33. !
	/// </summary>
	public static final byte EXCLAMAMATION_MARK = 33;

	/// <summary>
	/// 34. "
	/// </summary>
	public static final byte DOUBLE_QUOTES = 34;

	/// <summary>
	/// 35. #
	/// </summary>
	public static final byte NUMBER_SIGN = 35;

	/// <summary>
	/// 36. $
	/// </summary>
	public static final byte DOLLAR_SIGN = 36;

	/// <summary>
	/// 37. %
	/// </summary>
	public static final byte PERCENT_SIGN = 37;

	/// <summary>
	/// 38. &amp;
	/// </summary>
	public static final byte AMPERSAND = 38;

	/// <summary>
	/// 39. '
	/// </summary>
	public static final byte SINGLE_QUOTE = 39;

	/// <summary>
	/// 40. (
	/// </summary>
	public static final byte OPENING_ROUND_BRACKET = 40;

	/// <summary>
	/// 41. )
	/// </summary>
	public static final byte CLOSING_ROUND_BRACKET = 41;

	/// <summary>
	/// 42. *
	/// </summary>
	public static final byte ASTERISK = 42;

	/// <summary>
	/// 43. +
	/// </summary>
	public static final byte PLUS = 43;

	/// <summary>
	/// 44. ,
	/// </summary>
	public static final byte COMMA = 44;

	/// <summary>
	/// 45. -
	/// </summary>
	public static final byte MINUS = 45;

	/// <summary>
	/// 46. .
	/// </summary>
	public static final byte DOT = 46;

	/// <summary>
	/// 47. /
	/// </summary>
	public static final byte SLASH = 47;

	/// <summary>
	/// 48. 0
	/// </summary>
	public static final byte NUMBER_ZERO = 48;

	/// <summary>
	/// 49. 1
	/// </summary>
	public static final byte NUMBER_ONE = 49;

	/// <summary>
	/// 50. 2
	/// </summary>
	public static final byte NUMBER_TWO = 50;

	/// <summary>
	/// 51. 3
	/// </summary>
	public static final byte NUMBER_THREE = 51;

	/// <summary>
	/// 52. 4
	/// </summary>
	public static final byte NUMBER_FOUR = 52;

	/// <summary>
	/// 53. 5
	/// </summary>
	public static final byte NUMBER_FIVE = 53;

	/// <summary>
	/// 54. 6
	/// </summary>
	public static final byte NUMBER_SIV = 54;

	/// <summary>
	/// 55. 7
	/// </summary>
	public static final byte NUMBER_SEVEN = 55;

	/// <summary>
	/// 56. 8
	/// </summary>
	public static final byte NUMBER_EIGHT = 56;

	/// <summary>
	/// 57. 9
	/// </summary>
	public static final byte NUMBER_NINE = 57;

	/// <summary>
	/// 58. :
	/// </summary>
	public static final byte COLON = 58;

	/// <summary>
	/// 59. ;
	/// </summary>
	public static final byte SEMICOLON = 59;

	/// <summary>
	/// 60. &lt;
	/// </summary>
	public static final byte LESS_THAN_SIGN = 60;

	/// <summary>
	/// 61. =
	/// </summary>
	public static final byte EQUALS_SIGN = 61;

	/// <summary>
	/// 62. >
	/// </summary>
	public static final byte GREATER_THAN_SIGN = 62;

	/// <summary>
	/// 63. ?
	/// </summary>
	public static final byte QUESTION_MARK = 63;

	/// <summary>
	/// 64. @
	/// </summary>
	public static final byte AT_SIGN = 64;

	/// <summary>
	/// 65. A
	/// </summary>
	public static final byte CAPITAL_LETTER_A = 65;

	/// <summary>
	/// 66. B
	/// </summary>
	public static final byte CAPITAL_LETTER_B = 66;

	/// <summary>
	/// 67. C
	/// </summary>
	public static final byte CAPITAL_LETTER_C = 67;

	/// <summary>
	/// 68. D
	/// </summary>
	public static final byte CAPITAL_LETTER_D = 68;

	/// <summary>
	/// 69. E
	/// </summary>
	public static final byte CAPITAL_LETTER_E = 69;

	/// <summary>
	/// 70. F
	/// </summary>
	public static final byte CAPITAL_LETTER_F = 70;

	/// <summary>
	/// 71. G
	/// </summary>
	public static final byte CAPITAL_LETTER_G = 71;

	/// <summary>
	/// 72. H
	/// </summary>
	public static final byte CAPITAL_LETTER_H = 72;

	/// <summary>
	/// 73. I
	/// </summary>
	public static final byte CAPITAL_LETTER_I = 73;

	/// <summary>
	/// 74. J
	/// </summary>
	public static final byte CAPITAL_LETTER_J = 74;

	/// <summary>
	/// 75. K
	/// </summary>
	public static final byte CAPITAL_LETTER_K = 75;

	/// <summary>
	/// 76. L
	/// </summary>
	public static final byte CAPITAL_LETTER_L = 76;

	/// <summary>
	/// 77. M
	/// </summary>
	public static final byte CAPITAL_LETTER_M = 77;

	/// <summary>
	/// 78. N
	/// </summary>
	public static final byte CAPITAL_LETTER_N = 78;

	/// <summary>
	/// 79. O
	/// </summary>
	public static final byte CAPITAL_LETTER_O = 79;

	/// <summary>
	/// 80. P
	/// </summary>
	public static final byte CAPITAL_LETTER_P = 80;

	/// <summary>
	/// 81. Q
	/// </summary>
	public static final byte CAPITAL_LETTER_Q = 81;

	/// <summary>
	/// 82. R
	/// </summary>
	public static final byte CAPITAL_LETTER_R = 82;

	/// <summary>
	/// 83. S
	/// </summary>
	public static final byte CAPITAL_LETTER_S = 83;

	/// <summary>
	/// 84. T
	/// </summary>
	public static final byte CAPITAL_LETTER_T = 84;

	/// <summary>
	/// 85. U
	/// </summary>
	public static final byte CAPITAL_LETTER_U = 85;

	/// <summary>
	/// 86. W
	/// </summary>
	public static final byte CAPITAL_LETTER_W = 86;

	/// <summary>
	/// 87. V
	/// </summary>
	public static final byte CAPITAL_LETTER_V = 87;

	/// <summary>
	/// 88. X
	/// </summary>
	public static final byte CAPITAL_LETTER_X = 88;

	/// <summary>
	/// 89. Y
	/// </summary>
	public static final byte CAPITAL_LETTER_Y = 89;

	/// <summary>
	/// 90. Z
	/// </summary>
	public static final byte CAPITAL_LETTER_Z = 90;

	/// <summary>
	/// 91. [
	/// </summary>
	public static final byte OPENING_SQUARE_BRACKET = 91;

	/// <summary>
	/// 92. \
	/// </summary>
	public static final byte BACKSLASH = 92;

	/// <summary>
	/// 93. ]
	/// </summary>
	public static final byte CLOSING_SQUARE_BRACKET = 93;

	/// <summary>
	/// 94. ^
	/// </summary>
	public static final byte CARET = 94;

	/// <summary>
	/// 95. _
	/// </summary>
	public static final byte UNDERSCORE = 95;

	/// <summary>
	/// 96. `
	/// </summary>
	public static final byte GRAVE_ACCENT = 96;

	/// <summary>
	/// 97. a
	/// </summary>
	public static final byte LOWERCASE_LETTER_A = 97;

	/// <summary>
	/// 98. b
	/// </summary>
	public static final byte LOWERCASE_LETTER_B = 98;

	/// <summary>
	/// 99. c
	/// </summary>
	public static final byte LOWERCASE_LETTER_C = 99;

	/// <summary>
	/// 100. d
	/// </summary>
	public static final byte LOWERCASE_LETTER_D = 100;

	/// <summary>
	/// 101. e
	/// </summary>
	public static final byte LOWERCASE_LETTER_E = 101;

	/// <summary>
	/// 102. f
	/// </summary>
	public static final byte LOWERCASE_LETTER_F = 102;

	/// <summary>
	/// 103. g
	/// </summary>
	public static final byte LOWERCASE_LETTER_G = 103;

	/// <summary>
	/// 104. h
	/// </summary>
	public static final byte LOWERCASE_LETTER_H = 104;

	/// <summary>
	/// 105. i
	/// </summary>
	public static final byte LOWERCASE_LETTER_I = 105;

	/// <summary>
	/// 106. j
	/// </summary>
	public static final byte LOWERCASE_LETTER_J = 106;

	/// <summary>
	/// 107. k
	/// </summary>
	public static final byte LOWERCASE_LETTER_K = 107;

	/// <summary>
	/// 108. l
	/// </summary>
	public static final byte LOWERCASE_LETTER_L = 108;

	/// <summary>
	/// 109. m
	/// </summary>
	public static final byte LOWERCASE_LETTER_M = 109;

	/// <summary>
	/// 110. n
	/// </summary>
	public static final byte LOWERCASE_LETTER_N = 110;

	/// <summary>
	/// 111. o
	/// </summary>
	public static final byte LOWERCASE_LETTER_O = 111;

	/// <summary>
	/// 112. p
	/// </summary>
	public static final byte LOWERCASE_LETTER_P = 112;

	/// <summary>
	/// 113. q
	/// </summary>
	public static final byte LOWERCASE_LETTER_Q = 113;

	/// <summary>
	/// 114. r
	/// </summary>
	public static final byte LOWERCASE_LETTER_R = 114;

	/// <summary>
	/// 115. s
	/// </summary>
	public static final byte LOWERCASE_LETTER_S = 115;

	/// <summary>
	/// 116. t
	/// </summary>
	public static final byte LOWERCASE_LETTER_T = 116;

	/// <summary>
	/// 117. u
	/// </summary>
	public static final byte LOWERCASE_LETTER_U = 117;

	/// <summary>
	/// 118. w
	/// </summary>
	public static final byte LOWERCASE_LETTER_W = 118;

	/// <summary>
	/// 119. v
	/// </summary>
	public static final byte LOWERCASE_LETTER_V = 119;

	/// <summary>
	/// 120. x
	/// </summary>
	public static final byte LOWERCASE_LETTER_X = 120;

	/// <summary>
	/// 121. y
	/// </summary>
	public static final byte LOWERCASE_LETTER_Y = 121;

	/// <summary>
	/// 122. z
	/// </summary>
	public static final byte LOWERCASE_LETTER_Z = 122;

	/// <summary>
	/// 123. {
	/// </summary>
	public static final byte OPENING_CURLY_BRACKET = 123;

	/// <summary>
	/// 124. |
	/// </summary>
	public static final byte VERTICAL_SLASH = 124;

	/// <summary>
	/// 125. }
	/// </summary>
	public static final byte CLOSING_CURLY_BRACKET = 125;

	/// <summary>
	/// 126. ~
	/// </summary>
	public static final byte SWUNG_DASH = 126;

	/// <summary>
	/// 127. Delete
	/// </summary>
	public static final byte DEL = 127;
}