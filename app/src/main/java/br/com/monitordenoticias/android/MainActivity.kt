package br.com.monitordenoticias.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { MonitorApp() }
    }
}

private val Navy = Color(0xFF061A38)
private val Navy2 = Color(0xFF0A2A54)
private val CardBlue = Color(0xFF0D3566)
private val Cyan = Color(0xFF2CC7FF)
private val Green = Color(0xFF4DDB8A)
private val Purple = Color(0xFFAE7BFF)
private val Orange = Color(0xFFFFB548)
private val TextSoft = Color(0xFFB6C8DF)

@Composable
fun MonitorApp() {
    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = Cyan,
            background = Navy,
            surface = Navy2,
            onBackground = Color.White,
            onSurface = Color.White
        )
    ) {
        Scaffold(
            containerColor = Color.Transparent,
            contentWindowInsets = WindowInsets.safeDrawing,
            bottomBar = { BottomNav() }
        ) { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(listOf(Navy2, Navy, Color(0xFF031126)))
                    )
                    .padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                item { Header() }
                item { StatusHero() }
                item { MetricsRow() }
                item { VideoPanel() }
                item {
                    Text("Últimas notícias", fontSize = 19.sp, fontWeight = FontWeight.Bold)
                }
                items(sampleNews) { news -> NewsCard(news) }
                item { Spacer(Modifier.height(12.dp)) }
            }
        }
    }
}

@Composable
private fun Header() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(48.dp).background(Cyan.copy(.18f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Radar, null, tint = Cyan, modifier = Modifier.size(30.dp))
        }
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text("VIGILÂNCIA • ANÁLISE", color = Cyan, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            Text("Monitor de Notícias", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
            Text("v4.2.0 • ambiente de desenvolvimento", color = TextSoft, fontSize = 12.sp)
        }
        IconButton(onClick = {}) { Icon(Icons.Default.MoreVert, "Menu") }
    }
}

@Composable
private fun StatusHero() {
    Card(
        colors = CardDefaults.cardColors(containerColor = CardBlue.copy(.92f)),
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(10.dp).background(Green, CircleShape))
                Spacer(Modifier.width(8.dp))
                Text("Monitoramento ativo", color = Green, fontWeight = FontWeight.Bold)
            }
            Text("Central pronta para monitorar", fontSize = 22.sp, fontWeight = FontWeight.ExtraBold)
            Text(
                "Buscas e resultados organizados em uma tela rápida, segura e adaptada ao telefone.",
                color = TextSoft
            )
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                InfoPill(Icons.Default.Schedule, "Último disparo", "--:--", Modifier.weight(1f))
                InfoPill(Icons.Default.Sync, "Próxima busca", "automática", Modifier.weight(1f))
            }
            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(Icons.Default.Refresh, null)
                Spacer(Modifier.width(8.dp))
                Text("Atualizar agora", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun InfoPill(icon: ImageVector, label: String, value: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        color = Color.White.copy(.07f),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(Modifier.padding(12.dp)) {
            Icon(icon, null, tint = Cyan, modifier = Modifier.size(18.dp))
            Spacer(Modifier.height(8.dp))
            Text(label, color = TextSoft, fontSize = 11.sp)
            Text(value, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        }
    }
}

@Composable
private fun MetricsRow() {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        MetricCard("0", "Notícias 24h", Icons.Default.Article, Cyan, Modifier.weight(1f))
        MetricCard("0", "Demandas", Icons.Default.Assignment, Orange, Modifier.weight(1f))
    }
}

@Composable
private fun MetricCard(value: String, label: String, icon: ImageVector, accent: Color, modifier: Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = CardBlue.copy(.75f)),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Icon(icon, null, tint = accent)
            Spacer(Modifier.height(12.dp))
            Text(value, fontSize = 28.sp, fontWeight = FontWeight.ExtraBold)
            Text(label, color = TextSoft, fontSize = 12.sp)
        }
    }
}

@Composable
private fun VideoPanel() {
    Card(
        colors = CardDefaults.cardColors(containerColor = CardBlue.copy(.72f)),
        shape = RoundedCornerShape(22.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.PlayCircle, null, tint = Purple)
                Spacer(Modifier.width(10.dp))
                Text("Monitor de Vídeos", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                MiniStat("0", "Encontrados", Purple, Modifier.weight(1f))
                MiniStat("0", "Hoje", Green, Modifier.weight(1f))
                MiniStat("0", "Fontes", Cyan, Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun MiniStat(value: String, label: String, accent: Color, modifier: Modifier) {
    Surface(modifier, color = Color.White.copy(.06f), shape = RoundedCornerShape(16.dp)) {
        Column(Modifier.padding(12.dp)) {
            Text(value, color = accent, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
            Text(label, color = TextSoft, fontSize = 11.sp)
        }
    }
}

data class NewsItem(val source: String, val title: String, val tag: String)

private val sampleNews = listOf(
    NewsItem("Monitor", "Aguardando a primeira atualização de notícias", "SISTEMA"),
    NewsItem("Fontes", "As notícias reais serão ligadas à camada de dados da versão estável", "PRÓXIMA ETAPA")
)

@Composable
private fun NewsCard(news: NewsItem) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(.055f)),
        shape = RoundedCornerShape(18.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(Modifier.padding(14.dp), verticalAlignment = Alignment.Top) {
            Box(
                Modifier.size(44.dp).background(Cyan.copy(.12f), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) { Icon(Icons.Default.Newspaper, null, tint = Cyan) }
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(news.source, color = TextSoft, fontSize = 11.sp)
                Text(
                    news.title,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(8.dp))
                Surface(color = Cyan.copy(.12f), shape = RoundedCornerShape(20.dp)) {
                    Text(news.tag, color = Cyan, fontSize = 10.sp, modifier = Modifier.padding(horizontal = 9.dp, vertical = 4.dp))
                }
            }
        }
    }
}

@Composable
private fun BottomNav() {
    NavigationBar(containerColor = Color(0xFF06172F)) {
        val items = listOf(
            Triple("Início", Icons.Default.Home, true),
            Triple("Vídeos", Icons.Default.PlayCircle, false),
            Triple("Fontes", Icons.Default.Storage, false),
            Triple("Demandas", Icons.Default.Assignment, false),
            Triple("Mais", Icons.Default.Menu, false)
        )
        items.forEach { (label, icon, selected) ->
            NavigationBarItem(
                selected = selected,
                onClick = {},
                icon = { Icon(icon, label) },
                label = { Text(label, fontSize = 10.sp) }
            )
        }
    }
}
