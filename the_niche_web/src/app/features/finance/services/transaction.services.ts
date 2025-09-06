import {Budget, Transaction} from "../finance.model";
import {BehaviorSubject, Observable} from "rxjs";
import {Injectable} from "@angular/core";


@Injectable({providedIn: 'root'})
export class TransactionService {
    getAllTransactions():Observable<Transaction[]> {
        return new BehaviorSubject<Transaction[]>(transactions);
    }
}
const transactions :Transaction[]=
    [
        {
            "id": "t-101",
            "type": "income",
            "amount": 5000,
            "description": "Monthly salary",
            "date": "2025-09-01",
            "paymentMethod": "Bank Transfer",
            "category": "Salary",
            "budget": "Household"
        },
        {
            "id": "t-102",
            "type": "expense",
            "amount": 200,
            "description": "Groceries shopping",
            "date": "2025-09-02",
            "paymentMethod": "Debit Card",
            "category": "Food & Groceries",
            "budget": "Household"
        },
        {
            "id": "t-103",
            "type": "expense",
            "amount": 80,
            "description": "Gas refill",
            "date": "2025-09-02",
            "paymentMethod": "Cash",
            "category": "Transport",
            "budget": "Commuting"
        },
        {
            "id": "t-104",
            "type": "expense",
            "amount": 150,
            "description": "Dinner with friends",
            "date": "2025-09-03",
            "paymentMethod": "Credit Card",
            "category": "Dining",
            "budget": "Entertainment"
        },
        {
            "id": "t-105",
            "type": "income",
            "amount": 1200,
            "description": "Freelance project",
            "date": "2025-09-04",
            "paymentMethod": "Bank Transfer",
            "category": "Freelance",
            "budget": "Side Income"
        },
        {
            "id": "t-106",
            "type": "expense",
            "amount": 300,
            "description": "Utility bill payment",
            "date": "2025-09-04",
            "paymentMethod": "Debit Card",
            "category": "Utilities",
            "budget": "Household"
        },
        {
            "id": "t-107",
            "type": "Transfer",
            "amount": 1000,
            "description": "Transfer to savings account",
            "date": "2025-09-05",
            "paymentMethod": "Bank Transfer",
            "category": "Savings",
            "budget": "Emergency Fund"
        },
        {
            "id": "t-108",
            "type": "expense",
            "amount": 250,
            "description": "Gym membership",
            "date": "2025-09-05",
            "paymentMethod": "Credit Card",
            "category": "Health & Fitness",
            "budget": "Personal"
        },
        {
            "id": "t-109",
            "type": "income",
            "amount": 500,
            "description": "Sold old guitar",
            "date": "2025-09-06",
            "paymentMethod": "Cash",
            "category": "Sales",
            "budget": "Extra Income"
        },
        {
            "id": "t-110",
            "type": "expense",
            "amount": 600,
            "description": "Online course subscription",
            "date": "2025-09-06",
            "paymentMethod": "Credit Card",
            "category": "Education",
            "budget": "Personal Development"
        }
    ]
