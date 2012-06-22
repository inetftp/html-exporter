package uk.co.certait.htmlexporter.writer.ods;

import org.odftoolkit.odfdom.type.Color;
import org.odftoolkit.simple.style.Border;
import org.odftoolkit.simple.style.Font;
import org.odftoolkit.simple.style.StyleTypeDefinitions;
import org.odftoolkit.simple.style.StyleTypeDefinitions.CellBordersType;
import org.odftoolkit.simple.style.StyleTypeDefinitions.FontStyle;
import org.odftoolkit.simple.style.StyleTypeDefinitions.HorizontalAlignmentType;
import org.odftoolkit.simple.table.Cell;

import uk.co.certait.htmlexporter.css.CssColorProperty;
import uk.co.certait.htmlexporter.css.CssIntegerProperty;
import uk.co.certait.htmlexporter.css.CssStringProperty;
import uk.co.certait.htmlexporter.css.Style;

public class OdsStyleGenerator
{
	public void styleCell(Cell cell, Style style)
	{
		if (style.isBackgroundSet())
		{
			cell.setCellBackgroundColor(new Color(style.getProperty(CssColorProperty.BACKGROUND)));
		}

		applyBorder(cell, style);
		applyAlignment(cell, style);
		applyFont(cell, style);
	}
	
	protected void applyBorder(Cell cell, Style style)
	{
		if (style.isBorderWidthSet())
		{
			int borderWidth = style.getProperty(CssIntegerProperty.BORDER_WIDTH);
			Color borderColor;

			if (style.isBorderColorSet())
			{
				borderColor = new Color(style.getProperty(CssColorProperty.BORDER_COLOR));
			}
			else
			{
				borderColor = Color.BLACK;
			}

			cell.setBorders(CellBordersType.ALL_FOUR, new Border(borderColor, borderWidth,
					StyleTypeDefinitions.SupportedLinearMeasure.PT));
		}	
	}

	protected void applyAlignment(Cell cell, Style style)
	{

		if (style.isLeftAligned())
		{
			cell.setHorizontalAlignment(HorizontalAlignmentType.LEFT);
		}
		else if (style.isRightAligned())
		{
			cell.setHorizontalAlignment(HorizontalAlignmentType.RIGHT);
		}
		else if (style.isCenterAligned())
		{
			cell.setHorizontalAlignment(HorizontalAlignmentType.CENTER);
		}

	}

	protected void applyFont(Cell cell, Style style)
	{
		Font font = cell.getFont();

		if (style.isFontNameSet())
		{
			font.setFamilyName(style.getProperty(CssStringProperty.FONT_FAMILY));
		}

		if (style.isFontBold() && style.isFontItalic())
		{
			font.setFontStyle(FontStyle.BOLDITALIC);
		}
		else if (style.isFontBold())
		{
			font.setFontStyle(FontStyle.BOLD);
		}
		else if (style.isFontItalic())
		{
			font.setFontStyle(FontStyle.ITALIC);
		}

		if (style.isFontSizeSet())
		{
			font.setSize(style.getProperty(CssIntegerProperty.FONT_SIZE));
		}

		if (style.isColorSet())
		{
			font.setColor(new Color(style.getProperty(CssColorProperty.COLOR)));
		}

		cell.setFont(font);
	}
}
