import express from 'express';
import bodyParser from 'body-parser';
import { TransactionService } from './services/transactionService';

const app = express();
const port = 3000;

app.use(bodyParser.json());

const transactionService = new TransactionService();

app.post('/transactions', (req, res) => {
    const transaction = req.body;
    transactionService.addTransaction(transaction);
    res.status(201).send(transaction);
});

app.get('/transactions', (req, res) => {
    const transactions = transactionService.getTransactions();
    res.status(200).send(transactions);
});

app.listen(port, () => {
    console.log(`Personal Finance Visualizer Stage 1 running at http://localhost:${port}`);
});