from pathlib import Path

PATH = Path("app/src/main/java/br/com/monitordenoticias/android/V30Screens.kt")
s = PATH.read_text(encoding="utf-8")


def patch_block(start: str, end: str, replacements: list[tuple[str, str]]) -> None:
    global s
    a = s.index(start)
    b = s.index(end, a)
    block = s[a:b]
    original = block
    for old, new in replacements:
        if old not in block:
            raise RuntimeError(f"Trecho esperado não encontrado em {start}: {old!r}")
        block = block.replace(old, new, 1)
    if block == original:
        raise RuntimeError(f"Nenhuma alteração aplicada em {start}")
    s = s[:a] + block + s[b:]


# O layout original reservava 144dp para o radar e 92dp para o botão dentro de
# uma tela de ~355dp. Isso deixava a coluna de texto com poucos pixels e fazia
# palavras inteiras quebrarem letra por letra.
patch_block(
    "private fun V40MonitoringCard",
    "@Composable\nprivate fun V40RadarGraphic",
    [
        ("Spacer(Modifier.width(16.dp))", "Spacer(Modifier.width(10.dp))"),
        ("Spacer(Modifier.width(14.dp))", "Spacer(Modifier.width(8.dp))"),
        ("modifier = Modifier.size(92.dp)", "modifier = Modifier.size(56.dp)"),
        ("modifier = Modifier.size(42.dp)", "modifier = Modifier.size(26.dp)"),
    ],
)

patch_block(
    "private fun V40RadarGraphic",
    "@Composable\nprivate fun V40MetricCard",
    [
        (".size(144.dp)", ".size(82.dp)"),
        ("Canvas(Modifier.size(122.dp))", "Canvas(Modifier.size(68.dp))"),
    ],
)

# Os cartões de 50% da largura tinham ícone de 72dp, padding de 28dp e seta de
# 22dp. Em celulares sobrava largura insuficiente para o título.
patch_block(
    "private fun V40MetricCard",
    "@Composable\nprivate fun V40VideosCard",
    [
        ("Row(Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically)", "Row(Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically)"),
        ("Modifier.size(72.dp).clip(RoundedCornerShape(18.dp))", "Modifier.size(40.dp).clip(RoundedCornerShape(14.dp))"),
        ("modifier = Modifier.size(34.dp)", "modifier = Modifier.size(24.dp)"),
        ("Spacer(Modifier.width(12.dp))", "Spacer(Modifier.width(6.dp))"),
        ("fontSize = 12.5.sp, fontWeight = FontWeight.SemiBold, lineHeight = 15.sp", "fontSize = 11.sp, fontWeight = FontWeight.SemiBold, lineHeight = 13.sp"),
        ("fontSize = 31.sp", "fontSize = 28.sp"),
        ("modifier = Modifier.size(22.dp)", "modifier = Modifier.size(16.dp)"),
    ],
)

patch_block(
    "private fun V40TinyStat",
    "@Composable\nprivate fun V40LatestNewsCard",
    [
        ("Modifier.padding(horizontal = 12.dp, vertical = 12.dp)", "Modifier.padding(horizontal = 8.dp, vertical = 10.dp)"),
        ("fontSize = 24.sp", "fontSize = 22.sp"),
        ("fontSize = 10.5.sp, lineHeight = 13.sp", "fontSize = 9.5.sp, lineHeight = 11.5.sp, maxLines = 2, overflow = TextOverflow.Ellipsis"),
    ],
)

PATH.write_text(s, encoding="utf-8")
print("Layout V40 ajustado para celulares compactos.")
