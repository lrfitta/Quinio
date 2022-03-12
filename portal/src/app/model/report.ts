export interface Report {
    startWeek: Date,
    numberWeek: number,
    bonusTransaction: number,
    sales: number,
    bonusAmount: number,
    redemptionTransaction: number,
    redeemedAmount: number,
    expireTransaction: number,
    expireAmount: number,
    availableBalance: number
}