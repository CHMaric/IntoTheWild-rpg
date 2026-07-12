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
> [!NOTE]
> Di seguito sono definite le principali caratteristiche di progetto. Per ulteriori informazioni si faccia riferimento alla wiki.

## Funzionalità
- Scelta dell'habitat. Ogni habitat ha a disposizione un pool di animali che lo abitano.
- Scelta dell'animale con cui giocare. Ogni animale ha annessa una differente difficoltà, che si evidenzia già nelle statistiche di partenza.
- Sfide personalizzate per ogni animale, prevedono una scelta binaria con outcome che impatta le statistiche (vita, energia, stamina).
- Possibilità di tornare ad Nido tra una sfida e l'altra per salvare o caricare partite di gioco.

## Architettura di Progetto
L'architettura di progetto è basata sullo schema MVC.
Il progetto è pertanto suddiviso in tre macro packages principali: core, persistence, ui.
Sono presenti inoltre nella cartella resources: challenges, images, style, view.

## Progettazione strutturale
Il progetto ha inoltre cercato di seguire per quanto possibile i principi SOLID e clean code per favorire espandibilità e manutenibilità.
A tale scopo sono inoltre stati impiegati i seguenti design pattern: Abstract Factory e Facade.

## Persistenza dei dati
Nel progetto si è scelto di peristere i dati attraverso JSON tramite libreria Gson.
La scelta di definire diverse interfacce permette tuttavia di prevedere la possibile sostituzione in futuro con differenti meccanismi di persistenza.

## UI
Nel progetto il cambio di schermata è gestito tramite la classe SceneManager, che si occupa di recuperare i vari controller quando appropriato.

---
## Uso di strumenti di AI
Durante lo sviluppo sono stati utilizzati strumenti di AI come supporto allo studio e per la generazione di codice ripetitivo come getter, setter e costruttori.
Tutto il codice è stato tuttavia revisionato, modificato e corretto personalmente.
