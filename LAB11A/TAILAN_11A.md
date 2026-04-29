# Лабораторийн ажлын тайлан (11a)

**Хичээл:** F.CSA311 Программ хангамжийн бүтээлт  
**Лаб №:** 11a  
**Сэдэв:** TicTacToe (X/O) Client-Server хувилбар сайжруулалт

---

## 1. Ажлын зорилго

Энэхүү лабораторийн ажлын зорилго нь React + TypeScript (frontend) болон Java NanoHTTPD (backend) архитектуртай TicTacToe төслийг уншиж ойлгон, дараах 3 сайжруулалтыг хийхэд оршино:

- `instructions` хэсэгт дараагийн тоглогч/ялагчийг харуулах
- `Undo` функцийг бүрэн ажиллуулах (multi-step undo)
- UI/CSS загварыг сайжруулах

---

## 2. Ашигласан технологи

- Frontend: React, TypeScript, CSS
- Backend: Java, NanoHTTPD
- Build tools: npm, Maven
- Архитектур: Client-Server (frontend `:3000`, backend `:8080`)

---

## 3. Хийж хэрэгжүүлсэн ажил

## 3.1 Instructions нэмэлт

Backend талаас `GameState` JSON хариунд:
- `instructions` (string)
- `canUndo` (boolean)

талбаруудыг нэмсэн.

`instructions` утга дараах логикоор тооцогдоно:
- Ялагч X бол: `Winner: X`
- Ялагч O бол: `Winner: O`
- Тэнцээ бол: `Draw game`
- Үгүй бол: `Next player: X` эсвэл `Next player: O`

Frontend дээр `App.tsx`-д `id="instructions"` хэсгийг render хийж, backend-с ирсэн утгыг бодитоор харуулдаг болгосон.

## 3.2 Undo функционал

Backend:
- `Game` class дээр `canUndo()` болон `undo()` method нэмсэн
- `App.java` дээр `/undo` endpoint нэмсэн
- Тоглоомын `history`-с хамгийн сүүлийн төлөв рүү буцах байдлаар multi-step undo хэрэгжүүлсэн

Frontend:
- `App.tsx` дээр `undo` async function нэмсэн
- `Undo` товчийг `/undo` endpoint-тай холбосон
- `canUndo=false` үед `Undo` товч disabled болно

## 3.3 Тоглоом дууссаны дараах төлөв

Backend дээр game дууссан үед (winner эсвэл draw):
- хоосон нүднүүд `playable=false` болно
- ингэснээр UI талд илүү ойлгомжтой, буруу нүүдэл хийх боломжгүй болсон

## 3.4 CSS/UI сайжруулалт

`App.css`-д дараах сайжруулалтууд хийсэн:
- төвлөрсөн board layout
- илүү цэвэр color palette ба shadow
- instructions panel
- button загвар (hover/disabled state)
- playable cell hover эффект

Үр дүнд нь өмнөх энгийн харагдацаас илүү ойлгомжтой, хэрэглэгчид ээлтэй UI болсон.

---

## 4. Өөрчилсөн файлууд

- `tictactoe-lab11/back-end/src/main/java/game/App.java`
- `tictactoe-lab11/back-end/src/main/java/game/Game.java`
- `tictactoe-lab11/back-end/src/main/java/game/GameState.java`
- `tictactoe-lab11/back-end/.gitignore`
- `tictactoe-lab11/front-end/src/App.tsx`
- `tictactoe-lab11/front-end/src/game.ts`
- `tictactoe-lab11/front-end/src/App.css`

---

## 5. Туршилт ба үр дүн

## 5.1 Backend compile

```bash
cd "tictactoe-lab11/back-end"
mvn -Dmaven.repo.local="$(pwd)/.m2" clean compile
```

**Үр дүн:** compile амжилттай.

## 5.2 Frontend build

```bash
npm --prefix "tictactoe-lab11/front-end" install
npm --prefix "tictactoe-lab11/front-end" run build
```
or
```bash
cd "/Users/macbook/Desktop/b lab 11/LAB11A/tictactoe-lab11" && (cd back-end && mvn -Dmaven.repo.local="$PWD/.m2" clean compile && mvn -Dmaven.repo.local="$PWD/.m2" exec:exec) & (cd front-end && npm install && npm start)
```

**Үр дүн:** build амжилттай.

## 5.3 Функцийн шалгалт

- New Game: самбар reset хийгддэг
- Нүүдэл хийхэд instructions дараагийн тоглогч руу шинэчлэгддэг
- Хожиход `Winner: X/O` гардаг
- Тэнцэхэд `Draw game` гардаг
- Undo товч олон алхам дараалан буцаадаг
- Эхний төлөв дээр Undo disabled байдаг

---

## 6. Дүгнэлт

Lab 11a-ын үндсэн шаардлагууд болох `instructions`, `undo`, `css` сайжруулалтыг frontend-backend урсгалтай нь бүрэн хэрэгжүүлж, ажиллуулах болон build түвшинд амжилттай баталгаажуулсан. Энэхүү ажил нь state удирдлага, client-server өгөгдөл солилцоо, UI/UX сайжруулалтын практик ойлголтыг бататгалаа.
