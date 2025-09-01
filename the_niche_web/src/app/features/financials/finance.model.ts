


export interface Budget{
    id?:number|string;
    name: string;
    limit: number;
    spent:number;
    remaining:number;
    period:string;
    startDate:string;
    endDate:string;
    transactions:Transaction[];
}

export interface Transaction {
    id: string; // unique identifier e.g. "t-101"
    type: "income" | "expense"; // only allow two types
    amount: number; // transaction amount
    description: string; // short description of the transaction
    date: string; // ISO date format "YYYY-MM-DD"
    paymentMethod: string; // e.g., "Cash", "Credit Card", "Debit Card"
}
