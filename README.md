# Atividade Kotlin - ICMS Tax Calculator

![Language](https://img.shields.io/badge/Language-Kotlin-7F52FF?style=flat-square)
![Platform](https://img.shields.io/badge/Platform-Android-3DDC84?style=flat-square)
![API Level](https://img.shields.io/badge/API-24+-brightgreen?style=flat-square)
![License](https://img.shields.io/badge/License-Open%20Source-blue?style=flat-square)

## 📋 Visão Geral

**Atividade Kotlin** é uma aplicação Android moderna desenvolvida em **Kotlin** que realiza cálculos de impostos ICMS (Imposto sobre Circulação de Mercadorias e Serviços) de forma prática e intuitiva. O aplicativo permite que o usuário informe o estado brasileiro, o valor do produto e receba instantaneamente o valor da taxa ICMS e o total com imposto.

A aplicação implementa um sistema de validação robusto, cálculos precisos de tributos estaduais brasileiros e uma interface amigável seguindo os padrões Material Design 3 do Android moderno.

---

## 🎯 Principais Funcionalidades

### 1. **Cálculo de ICMS por Estado Brasileiro**
- Suporte para múltiplos estados com alíquotas diferenciadas:
  - **17%**: Santa Catarina (SC), Espírito Santo (ES), Mato Grosso do Sul (MS), Rio Grande do Sul (RS)
  - **17.5%**: Goiás (GO)
  - **18%**: São Paulo (SP), Paraná (PR)
- Normalização automática de entrada (converte para maiúsculas)
- Validação de estado com feedback visual

### 2. **Validação de Entrada**
- Validação segura de valores numéricos
- Feedback visual de erros (exibição de erro no campo de entrada)
- Limpeza automática de campos de saída em caso de entrada inválida
- Suporte para entrada de valores decimais

### 3. **Cálculo Automático**
- Cálculo da taxa ICMS percentual
- Cálculo do total com imposto incluído
- Formatação automática de valores monetários
- Precisão em arredondamentos

### 4. **Interface Inteligente**
- **Código-cores visual**:
  - 🟢 **Verde**: Total com ICMS ≤ 17%
  - 🔴 **Vermelho**: Total com ICMS > 17%
- Layout responsivo e acessível
- Tema Material3 com suporte a modo escuro/claro
- Botão de cálculo com feedback imediato

### 5. **Testes Automatizados**
- Testes unitários JUnit4
- Testes instrumentados Android
- Validação de contexto e funcionalidade

---

## 🏗️ Decisões Arquiteturais e Internas

### **Arquitetura: Single Activity**
O projeto segue a arquitetura de **Single Activity Pattern**, onde toda a lógica de apresentação e negócio está centralizada em uma única `MainActivity`. Esta escolha foi deliberada para:
- ✅ Simplicidade e clareza de código
- ✅ Facilidade de manutenção em aplicações pequenas/médias
- ✅ Redução de overhead de fragmentos

### **Padrão de Vinculação: View Binding com findViewById()**
```kotlin
val stateInput: EditText = findViewById(R.id.et_state)
val valueInput: EditText = findViewById(R.id.et_value)
```
- Acesso direto aos elementos da interface
- Chamadas seguras sem reflexão
- Legibilidade máxima do fluxo de dados

### **Lógica de Negócio: When Expression**
```kotlin
val icmsRate = when (normalizedState) {
    "SC", "ES", "MS", "RS" -> 17.0
    "GO" -> 17.5
    "SP", "PR" -> 18.0
    else -> null
}
```
- Mapeamento limpo e legível de estado → alíquota
- Padrão funcional Kotlin
- Facilita adição de novos estados

### **Validação em Camadas**
1. **Entrada de texto** → Normalização (uppercase)
2. **Parsing numérico** → Try-catch para segurança
3. **Validação de estado** → Mapeamento when com null safety
4. **Validação de valor** → Verificação de positivo

### **Tema: Material3 DayNight NoActionBar**
- Tema moderno com suporte automático a modo escuro
- Remover ActionBar para usar Toolbar customizado (conforme necessário)
- Paleta de cores dinâmica (Light/Dark)

### **Sistema de Dependências: Version Catalog**
- Centralização de versões em `gradle/libs.versions.toml`
- Facilita manutenção e atualizações
- Padronização de dependências entre módulos

### **Configuração de Build: Gradle Kotlin DSL**
```kotlin
// app/build.gradle.kts
android {
    namespace = "com.example.myapplication"
    compileSdk = 36
    defaultConfig {
        minSdk = 24
        targetSdk = 36
    }
}
```
- Tipagem forte em tempo de compilação
- Melhor IDE support (autocomplete, refactoring)
- Compatibilidade com AGP moderno

---

## 🛠️ Stack Tecnológico

### **Linguagem & Runtime**
- **Kotlin** 1.9+ (100% do código-fonte)
- **Java 11** compatibility level

### **Android Framework**
- **minSdkVersion**: 24 (Android 7.0 - Nougat)
- **targetSdkVersion**: 36 (Android 15)
- **compileSdkVersion**: 36

### **Dependências Principais**
```
androidx:appcompat:1.6.1          # Compatibilidade com API antigas
androidx:core-ktx:1.12.0          # Extensões Kotlin para Android Core
androidx.material:material:1.11.0 # Material Design 3
androidx.activity:activity-ktx    # Extensões para Activity
androidx.constraintlayout          # Layout moderno e responsivo
```

### **Dependências de Teste**
```
junit:junit:4.13.2                # Testes unitários
androidx.test.espresso            # Testes instrumentados
androidx.test.ext:junit           # Runner de testes Android
androidx.test.ext:junit-ktx       # Extensões Kotlin para testes
```

### **Build System**
- **Gradle**: 9.4.1
- **AGP (Android Gradle Plugin)**: 8.7.0+

---

## 📊 Estrutura do Projeto

```
Atividade_Kotlin/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/myapplication/
│   │   │   │   └── MainActivity.kt              # Lógica principal + UI
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   └── activity_main.xml        # Layout da tela
│   │   │   │   ├── values/
│   │   │   │   │   ├── colors.xml
│   │   │   │   │   ├── strings.xml
│   │   │   │   │   └── themes.xml
│   │   │   │   ├── values-night/
│   │   │   │   │   └── themes.xml              # Tema modo escuro
│   │   │   │   └── xml/
│   │   │   │       ├── backup_rules.xml
│   │   │   │       └── data_extraction_rules.xml
│   │   │   ├── AndroidManifest.xml
│   │   │   └── keepRules/
│   │   │       └── rules.keep                   # Regras R8/ProGuard
│   │   ├── test/
│   │   │   └── ExampleUnitTest.kt               # Testes unitários
│   │   └── androidTest/
│   │       └── ExampleInstrumentedTest.kt       # Testes instrumentados
│   └── build.gradle.kts                         # Configuração do módulo
│
├── gradle/
│   ├── libs.versions.toml                       # Version Catalog
│   ├── wrapper/
│   │   └── gradle-wrapper.properties
│   └── gradle-daemon-jvm.properties
│
├── build.gradle.kts                             # Configuração raiz
├── settings.gradle.kts                          # Repositórios e módulos
└── gradle.properties
```

---

## 🚀 Como Usar

### **Pré-requisitos**
- **Android Studio** Jellyfish (2023.3.1) ou superior
- **JDK 11** ou superior
- **Gradle** 9.4.1 (incluído no Gradle Wrapper)
- **SDK Android** API 36

### **Instalação**

1. **Clone o repositório**
   ```bash
   git clone https://github.com/T4yson/Atividade_Kotlin.git
   cd Atividade_Kotlin
   ```

2. **Abra no Android Studio**
   ```bash
   # No Android Studio:
   File → Open → Selecione a pasta Atividade_Kotlin
   ```

3. **Sincronize Gradle**
   - Android Studio sincronizará automaticamente
   - Ou via terminal: `./gradlew sync`

4. **Compile o projeto**
   ```bash
   ./gradlew assemble
   ```

5. **Execute no emulador ou dispositivo**
   ```bash
   ./gradlew installDebug
   ```

### **Uso da Aplicação**

1. **Insira o estado** (ex: SP, SC, RS, GO, etc)
2. **Insira o valor do produto** (ex: 100.00)
3. **Clique em "Calcular"**
4. **Visualize o resultado**:
   - Taxa ICMS (%)
   - Valor total com imposto
   - Código de cores (verde/vermelho) indicando faixa de alíquota

### **Exemplos de Uso**

| Estado | Valor | Taxa | Total | Cor |
|--------|-------|------|-------|-----|
| SP | R$ 100,00 | 18% | R$ 118,00 | 🔴 |
| SC | R$ 100,00 | 17% | R$ 117,00 | 🟢 |
| GO | R$ 50,00 | 17.5% | R$ 58,75 | 🟢 |

---

## 🧪 Testes

### **Executar Testes Unitários**
```bash
./gradlew test
```

### **Executar Testes Instrumentados**
```bash
./gradlew connectedAndroidTest
```

### **Cobertura de Testes**
- ✅ Teste de soma básica (ExampleUnitTest)
- ✅ Validação de contexto e package name (ExampleInstrumentedTest)
- 🔄 Potencial para: testes de validação ICMS, cálculos monetários, casos extremos

---

## 📝 Decisões de Segurança & Performance

### **Segurança**
- ✅ Validação robusta de entrada numérica com try-catch
- ✅ Null-safety com operadores Kotlin (`?.`, `let`, `when`)
- ✅ Backups de dados habilitados (conforme `backup_rules.xml`)
- ✅ Data extraction rules configuradas para Android 12+

### **Performance**
- ✅ Single Activity reduz overhead de ciclo de vida
- ✅ Cálculos síncronos (tamanho de dados negligenciável)
- ✅ Sem inicialização desnecessária de bibliotecas
- ✅ Java 11 com otimizações de compilador

### **Acessibilidade**
- ✅ Material3 com suporte a modo escuro
- ✅ Layout com ConstraintLayout responsivo
- 🔄 Potencial para: descrições de conteúdo (contentDescription), suporte a TalkBack

---

## 🔄 Próximas Melhorias Recomendadas (Roadmap)

- [ ] **Migração para MVVM** com Jetpack (ViewModel, LiveData)
- [ ] **Substituir findViewById por View Binding** (mais seguro)
- [ ] **Adicionar suporte a mais estados** e/ou alíquotas dinâmicas (API)
- [ ] **Testes abrangentes** com cobertura >80%
- [ ] **Histórico de cálculos** (Room Database)
- [ ] **Modo offline** com sincronização de alíquotas
- [ ] **Notificações** de mudanças em alíquotas (se integrado a API)
- [ ] **Dark Mode completo** customizado
- [ ] **Suporte a múltiplas moedas**
- [ ] **Comparativo entre estados** visual

---

## 📄 Licença

Este projeto é de **código aberto** e pode ser utilizado livremente para fins educacionais e comerciais.

---

## 👤 Autor

**T4yson**  
GitHub: [@T4yson](https://github.com/T4yson)  
Repositório: [Atividade_Kotlin](https://github.com/T4yson/Atividade_Kotlin)

---

## 📞 Suporte & Contribuições

Para dúvidas, sugestões ou contribuições:
- Abra uma **Issue** no repositório
- Envie um **Pull Request** com melhorias
- Entre em contato via GitHub

---

**Desenvolvido com ❤️ em Kotlin**

---

*Última atualização: Junho de 2026*
