import { Transaction } from '../models/transaction';

class TransactionService {
    private transactions: Transaction[] = [];

    addTransaction(transaction: Transaction): void {
        this.transactions.push(transaction);
    }

    getTransactions(): Transaction[] {
        return this.transactions;
    }

    getTransactionById(id: string): Transaction | undefined {
        return this.transactions.find(transaction => transaction.id === id);
    }

    deleteTransaction(id: string): void {
        this.transactions = this.transactions.filter(transaction => transaction.id !== id);
    }
}

export default new TransactionService();