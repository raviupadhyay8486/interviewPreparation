#!/usr/bin/env python3
"""Generate ATS-friendly PDF resume from markdown source."""

from pathlib import Path

from fpdf import FPDF

SECTION_HEADERS = {
    "PROFESSIONAL SUMMARY",
    "SKILLS",
    "PROFESSIONAL EXPERIENCE",
    "KEY ACHIEVEMENTS",
    "EDUCATION",
    "CERTIFICATIONS",
    "WORK AUTHORIZATION",
    "TARGET ROLES",
}

COMPANY_LINES = {
    "HTC Global Services Pvt Ltd",
    "Xebia IT Architects Pvt Ltd",
    "Tech Mahindra Pvt Ltd",
    "Napier Healthcare Solutions Pvt Ltd",
    "Napier Healthcare Pvt Ltd",
}


def build_pdf(source: Path, output: Path) -> None:
    pdf = FPDF()
    pdf.set_auto_page_break(auto=True, margin=12)
    pdf.set_margins(15, 12, 15)
    pdf.add_page()
    width = pdf.epw

    lines = source.read_text(encoding="utf-8").splitlines()
    skip_cert_placeholder = False

    for raw_line in lines:
        line = raw_line.strip()

        if not line:
            pdf.ln(2)
            continue

        if line.startswith("(Add only certifications"):
            skip_cert_placeholder = True
            continue
        if skip_cert_placeholder and line.startswith("WORK AUTHORIZATION"):
            skip_cert_placeholder = False
        if skip_cert_placeholder:
            continue

        if line == "RAVI RANJAN UPADHYAY":
            pdf.set_font("Helvetica", "B", 16)
            pdf.multi_cell(width, 7, line)
            pdf.ln(1)
            continue

        if line in SECTION_HEADERS:
            pdf.ln(3)
            pdf.set_font("Helvetica", "B", 11)
            pdf.multi_cell(width, 5, line)
            pdf.ln(1)
            pdf.set_font("Helvetica", size=9)
            continue

        if line in COMPANY_LINES:
            pdf.ln(2)
            pdf.set_font("Helvetica", "B", 10)
            pdf.multi_cell(width, 5, line)
            pdf.set_font("Helvetica", size=9)
            continue

        if line.startswith("Client:") or line.startswith("Healthcare Information System") or " — " in line and "Pvt Ltd" in line:
            pdf.set_font("Helvetica", "B", 9)
            pdf.multi_cell(width, 4, line)
            pdf.set_font("Helvetica", size=9)
            continue

        pdf.set_font("Helvetica", size=9)
        if line.startswith("- "):
            pdf.multi_cell(width, 4, "  " + line)
        elif line.startswith("Technologies Used:"):
            pdf.set_font("Helvetica", "I", 8)
            pdf.multi_cell(width, 3.5, line)
            pdf.set_font("Helvetica", size=9)
        else:
            pdf.multi_cell(width, 4, line)

    output.parent.mkdir(parents=True, exist_ok=True)
    pdf.output(str(output))


if __name__ == "__main__":
    base = Path(__file__).parent
    sources = [
        ("Ravi_Ranjan_Upadhyay_Resume_ATS_95.md", "Ravi_Ranjan_Upadhyay_Resume_ATS_95.pdf"),
        (
            "Ravi_Ranjan_Upadhyay_Resume_JD_Backend_Platform.md",
            "Ravi_Ranjan_Upadhyay_Resume_JD_Backend_Platform.pdf",
        ),
    ]
    for src_name, out_name in sources:
        source = base / src_name
        if source.exists():
            output = base / out_name
            build_pdf(source, output)
            print(f"Generated: {output}")
