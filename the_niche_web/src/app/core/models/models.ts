

export interface Transaction{
  id:number,
  date:Date,
  amount:number,
  category:number,
  account:number,
  note:string,
  description:string,
}


export interface  Unit{
  id:number,
  name:string,
  address1:string,
  address2:string
}
