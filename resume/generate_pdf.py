#!/usr/bin/env python3
"""Generate resume PDF from markdown source."""

from pathlib import Path

from fpdf import FPDF

SECTION_HEADERS = {
    "PROFESSIONAL SUMMARY",
    "CORE ARCHITECTURE COMPETENCIES",
    "PROFESSIONAL EXPERIENCE",
    "KEY ARCHITECTURE ACHIEVEMENTS",
    "EDUCATION",
    "TARGET ROLE / AVAILABILITY",
}


def build_pdf(source: Path, output: Path) -> None:
    pdf = FPDF()
    pdf.set_auto_page_break(auto=True, margin=12)
    pdf.set_margins(15, 12, 15)
    pdf.add_page()
    width = pdf.epw

    for raw_line in source.read_text(encoding="utf-8").splitlines():
        line = raw_line.strip()
        if not line:
            pdf.ln(2)
            continue

        if line == "RAVI RANJAN UPADHYAY":
            pdf.set_font("Helvetica", "B", 16)
            pdf.multi_cell(width, 7, line)
            pdf.ln(1)
            continue

        if line == "Java Architect | 14.5+ Years":
            pdf.set_font("Helvetica", "B", 11)
            pdf.multi_cell(width, 5, line)
            pdf.ln(1)
            pdf.set_font("Helvetica", size=9)
            continue

        if line.startswith("Java - AWS - Microservices"):
            pdf.set_font("Helvetica", "B", 10)
            pdf.multi_cell(width, 5, line)
            pdf.ln(1)
            pdf.set_font("Helvetica", size=9)
            continue

        if line in SECTION_HEADERS:
            pdf.ln(3)
            pdf.set_font("Helvetica", "B", 11)
            pdf.multi_cell(width, 5, line)
            pdf.ln(1)
            pdf.set_font("Helvetica", size=9)
            continue

        if "| HTC" in line or "| Xebia" in line or "| Tech Mahindra" in line or "| Napier" in line:
            pdf.ln(2)
            pdf.set_font("Helvetica", "B", 9)
            pdf.multi_cell(width, 4, line)
            pdf.set_font("Helvetica", size=9)
            continue

        if (
            line.startswith("State Farm")
            or line.startswith("Ancestry")
            or line.startswith("MATT")
            or line.startswith("MGM")
            or line.startswith("SetPlex")
            or line.startswith("Healthcare")
        ):
            pdf.set_font("Helvetica", "B", 9)
            pdf.multi_cell(width, 4, line)
            pdf.set_font("Helvetica", size=9)
            continue

        pdf.set_font("Helvetica", size=9)
        if line.startswith("- "):
            pdf.multi_cell(width, 4, "  " + line)
        elif line.startswith("Tech:"):
            pdf.set_font("Helvetica", "I", 8)
            pdf.multi_cell(width, 3.5, line)
            pdf.set_font("Helvetica", size=9)
        else:
            pdf.multi_cell(width, 4, line)

    pdf.output(str(output))


if __name__ == "__main__":
    base = Path(__file__).parent
    build_pdf(
        base / "Ravi_Ranjan_Upadhyay_Java_Architect.md",
        base / "Ravi_Ranjan_Upadhyay_Java_Architect.pdf",
    )
    print("Generated PDF")
