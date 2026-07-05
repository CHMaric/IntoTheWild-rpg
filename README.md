# Into The Wild
Progetto di Metodologie di Programmazione

Questo progetto consiste in un'applicazione Java che implementa un gioco di ruolo di tipo testuale. Dopo aver scelto un habitat e l'animale da impersonare,
il giocatore affronterà sfide personalizzate a scelta binaria di tipo fight or flight. Lo scopo è sopravvivere a tutte le sfide che la natura pone.
L'intero progetto è strutturato per favorire manutenibilità ed estendibilità futura, con la possibilità di inserimento di nuovi habitat e animali annessi
e nuove sfide.

---

## Come eseguire il progetto

### Prerequisiti
- Java 25 (LTS)
- Gradle


### Build del progetto
```bash
./gradlew build
```

### Esecuzione
```bash
./gradlew run
```

---
## Funzionalità
- Scelta dell'habitat. Ogni habitat ha a disposizione un pool di animali che lo abitano.
- Scelta dell'animale con cui giocare. Ogni animale ha annessa una differente difficoltà, che si evidenzia già nelle statistiche di partenza.
- Sfide personalizzate per ogni animale, prevedono una scelta binaria con outcome che impatta le statistiche (vita, energia, stamina).
- Possibilità di tornare ad Nido tra una sfida e l'altra per salvare o caricare partite di gioco.

## Architettura di Progetto
L'architettura di progetto è basata sullo schema MVC.

Il progetto è pertanto suddiviso in tre macro packages principali: core, persistence, ui.
Sono presenti inoltre nella cartella resources: challenges, images, style, view.

- core:
    - mechanics: classi contenenti le logiche di gioco.
    - model: package principale che contiene le entità di interesse
        - animals: classe astratta Animal + implementazioni
        - habitats: classe astratta Habitat + implementazioni

          - factory: interfaccia Habitat factory + implementazioni e HabitatRegitry  
    - dto: package per record

- persistence: classi per la peristenza

  - interfaces: interfacce implementate dalle classi concrete per la persistenza

- ui: contiene lo SceneManager per la navigazione tra le schermate
    - controller: controller per le view

Resources:
- challenges: file .json con le sfide personalizzate
- images: immagini che sono state usate nei file .fxml
- style: file .css riferiti ai file .fxml
- view: file .fxml

---
## Progettazione strutturale
Il progetto ha inoltre cercato di seguire per quanto possibile i principi SOLID e clean code.
Nell'applicazione è stato fatto uso di design pattern (oggetto di studio del terzo anno) per favorire espandibilità e manutenibilità. In particolare:

- Abstract Factory: permette la creazione di animali attinenti per ogni habitat. La classe HabitatRegistry è stata usata per mappare le implementazioni
concrete di HabitatFactory e label corrispondente, evitando accoppiamento stretto con la GameEngine e supportando l'estensione futura di nuovi habitat.

- Facade: la GameEngine funge da orchestratore principale della logica di gioco, coordinando la gestione del player, delle challenge, del salvataggio
e del caricamento dello stato.

## Persistenza dei dati
Nel progetto si è scelto di peristere i dati attraverso JSON tramite libreria Gson. La separazione delle responsabilità di salvataggio, caricamento e 
recupero dei dati di gioco è avvenuta tramite le interfacce SaveManager, ChallengeLoader e GamePersistenceService. La scelta di implementare tali interfacce
offre la possibilità futura di implementazioni concrete con differenti meccanismi di persistenza.

## UI
Nel progetto il cambio di schermata è gestito tramite la classe SceneManager, che si occupa di recuperare i vari controller quando appropriato.

---
## Uso di strumenti di AI
Durante lo sviluppo sono stati utilizzati strumenti di AI come supporto allo studio e per la generazione di codice ripetitivo. In particolare per:
- chiarire i meccanismi di utilizzo di SceneBuilder e avere suggerimenti circa gli stili css da usare per una veloce miglioria della grafica.
- generazione di setter, getter e costruttori tramite funzionalità interne di Intellij quando la gestione tramite annotation non si è rivelata appropriata.
- delucidazioni circa errori poco chiari e bug legati all'utilizzo di file binary pesanti.

Tutto il codice è stato tuttavia revisionato, modificato e corretto personalmente.
