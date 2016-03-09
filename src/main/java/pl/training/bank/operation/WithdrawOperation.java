package pl.training.bank.operation;

import pl.training.bank.entity.Account;

public class WithdrawOperation extends Operation {

    @Override
    public void execute() {
        Account account = accountsRepository.getByNumber(sourceAccountNumber);
        throwExceptionWhenNoSufficientFunds(account, funds);
        account.withdraw(funds);
        accountsRepository.update(account);
    }

    private void throwExceptionWhenNoSufficientFunds(Account account, long funds) {
        if (account.getBalance() < funds) {
            throw new InsufficientFundsException();
        }
    }

}
