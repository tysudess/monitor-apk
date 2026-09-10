from pathlib import Path

SCREENS = Path("app/src/main/java/br/com/monitordenoticias/android/V30Screens.kt")
MAIN = Path("app/src/main/java/br/com/monitordenoticias/android/MainActivityV28.kt")


def replace_function(text: str, start: str, end: str, replacement: str) -> str:
    a = text.index(start)
    b = text.index(end, a)
    return text[:a] + replacement.rstrip() + "\n\n" + text[b:]


screens = SCREENS.read_text(encoding="utf-8")

monitoring = r'''@Composable
private fun V40MonitoringCard(active: Boolean, busy: Boolean, subtitle: String, lastRun: String, onRefresh: () -> Unit) {
    Surface(
        color = Color(0xFF0A2043),
        shape = RoundedCornerShape(24.dp),
        border = BorderStroke(1.dp, Color(0xFF176CC7).copy(alpha = .70f)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(Modifier.fillMaxWidth().padding(14.dp)) {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                V40RadarGraphic()
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Surface(
                        color = Color(0xFF0E533A),
                        shape = RoundedCornerShape(50),
                        border = BorderStroke(1.dp, Color(0xFF2BCF96).copy(alpha = .45f))
                    ) {
                        Row(
                            Modifier.padding(horizontal = 9.dp, vertical = 5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(Modifier.size(8.dp).clip(RoundedCornerShape(50)).background(Color(0xFF42F3B8)))
                            Spacer(Modifier.width(6.dp))
                            Text(
                                if (active) "MONITORAMENTO ATIVO" else "MONITORAMENTO PAUSADO",
                                color = Color(0xFF42F3B8),
                                fontSize = 9.sp,
                                lineHeight = 11.sp,
                                fontWeight = FontWeight.ExtraBold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                    Spacer(Modifier.height(7.dp))
                    Text(
                        "Monitoramento em execução",
                        color = Color.White,
                        fontSize = 15.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        maxLines = 2
                    )
                    Spacer(Modifier.height(3.dp))
                    Text(
                        subtitle,
                        color = Color(0xFFB8CAE0),
                        fontSize = 10.5.sp,
                        lineHeight = 14.sp,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            Spacer(Modifier.height(12.dp))
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Outlined.Schedule, null, tint = Color(0xFF8EB8FF), modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(6.dp))
                Text(
                    "Último disparo: $lastRun",
                    color = Color(0xFFB9CDE3),
                    fontSize = 10.5.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
                Spacer(Modifier.width(8.dp))
                Surface(
                    onClick = onRefresh,
                    enabled = !busy,
                    color = Color(0xFF5CAEFF),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Row(
                        Modifier.padding(horizontal = 11.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            if (busy) Icons.Outlined.HourglassTop else Icons.Outlined.Refresh,
                            null,
                            tint = Color(0xFF051932),
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(Modifier.width(5.dp))
                        Text("Atualizar", color = Color(0xFF051932), fontSize = 10.sp, fontWeight = FontWeight.ExtraBold)
                    }
                }
            }
        }
    }
}'''

radar = r'''@Composable
private fun V40RadarGraphic() {
    Box(
        Modifier
            .size(76.dp)
            .clip(RoundedCornerShape(50))
            .background(Color(0xFF061F34)),
        contentAlignment = Alignment.Center
    ) {
        Canvas(Modifier.size(62.dp)) {
            val c = center
            drawCircle(color = Color(0xFF0C6B71), radius = size.minDimension / 2f, style = Stroke(width = 5f))
            drawCircle(color = Color(0xFF18D7B4).copy(alpha = .68f), radius = size.minDimension / 2.8f, style = Stroke(width = 4f))
            drawCircle(color = Color(0xFF18D7B4).copy(alpha = .85f), radius = size.minDimension / 6.5f, style = Stroke(width = 3.5f))
            val end = Offset(c.x + size.minDimension * 0.23f, c.y - size.minDimension * 0.18f)
            drawLine(color = Color(0xFF4EF2D2), start = c, end = end, strokeWidth = 6f)
            drawCircle(color = Color(0xFF4EF2D2), radius = 7f, center = end)
            drawCircle(color = Color(0xFF4EF2D2), radius = 6f, center = c)
        }
    }
}'''

metric = r'''@Composable
private fun V40MetricCard(title: String, value: String, footer: String, icon: ImageVector, color: Color, modifier: Modifier) {
    Surface(
        color = Color(0xFF0A1D39),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, color.copy(alpha = .38f)),
        modifier = modifier
    ) {
        Column(Modifier.fillMaxWidth().padding(11.dp)) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Box(
                    Modifier.size(42.dp).clip(RoundedCornerShape(13.dp)).background(color.copy(alpha = .12f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(icon, null, tint = color, modifier = Modifier.size(23.dp))
                }
                Spacer(Modifier.weight(1f))
                Icon(Icons.Outlined.ChevronRight, null, tint = Color(0xFFA5C9F5), modifier = Modifier.size(18.dp))
            }
            Spacer(Modifier.height(8.dp))
            Text(
                title,
                color = Color.White,
                fontSize = 11.sp,
                lineHeight = 13.sp,
                fontWeight = FontWeight.SemiBold,
                minLines = 2,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(2.dp))
            Text(value, color = color, fontSize = 27.sp, lineHeight = 30.sp, fontWeight = FontWeight.ExtraBold)
            Spacer(Modifier.height(2.dp))
            Text(
                footer,
                color = if (footer.contains("Sem")) Color(0xFF93A9C0) else Color(0xFF42F3B8),
                fontSize = 9.5.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}'''

screens = replace_function(
    screens,
    "@Composable\nprivate fun V40MonitoringCard",
    "@Composable\nprivate fun V40RadarGraphic",
    monitoring,
)
screens = replace_function(
    screens,
    "@Composable\nprivate fun V40RadarGraphic",
    "@Composable\nprivate fun V40MetricCard",
    radar,
)
screens = replace_function(
    screens,
    "@Composable\nprivate fun V40MetricCard",
    "@Composable\nprivate fun V40VideosCard",
    metric,
)
SCREENS.write_text(screens, encoding="utf-8")

main = MAIN.read_text(encoding="utf-8")
old_header_image = '''modifier = Modifier
                        .align(Alignment.TopEnd)
                        .width(208.dp)
                        .height(100.dp)
                        .clip(RoundedCornerShape(14.dp))'''
new_header_image = '''modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .width(168.dp)
                        .height(78.dp)
                        .clip(RoundedCornerShape(14.dp))'''
if old_header_image in main:
    main = main.replace(old_header_image, new_header_image, 1)

main = main.replace('.height(182.dp)', '.height(194.dp)', 1)
main = main.replace('fontSize = 17.sp, lineHeight = 19.sp', 'fontSize = 16.5.sp, lineHeight = 19.sp', 1)
main = main.replace('fontSize = 8.8.sp,\n                        fontWeight = FontWeight.Medium,\n                        letterSpacing = 1.2.sp', 'fontSize = 8.sp,\n                        fontWeight = FontWeight.Medium,\n                        letterSpacing = 0.8.sp', 1)
MAIN.write_text(main, encoding="utf-8")

print("Home V40 R3: layout responsivo aplicado em V30Screens.kt e MainActivityV28.kt")
