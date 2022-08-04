package com.cqrs.command.aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import com.cqrs.command.commands.AccountCreationCommand;
import com.cqrs.command.commands.DepositMoneyCommand;
import com.cqrs.command.commands.WithdrawMoneyCommand;
import com.cqrs.events.AccountCreationEvent;
import com.cqrs.events.DepositMoneyEvent;
import com.cqrs.events.WithdrawMoneyEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

@RequiredArgsConstructor
@Aggregate
@Slf4j
public class AccountAggregate {
  @AggregateIdentifier
  private String accountID;
  private String holderID;
  private Long balance;

  @CommandHandler
  public AccountAggregate(AccountCreationCommand command) {
    log.debug("handling {}",command);
    apply(new AccountCreationEvent(command.getHolderID(),command.getAccountID()));
  }
  @EventSourcingHandler
  protected void createAccount(AccountCreationEvent event){
    log.debug("applying {}",event);
    this.accountID = event.getAccountID();
    this.holderID = event.getHolderID();
    this.balance = 0L;
  }

  @CommandHandler
  protected void depositMoney(DepositMoneyCommand command){
    log.debug("handling {}",command);
    if(command.getAmount() <= 0) throw new IllegalStateException("amount >= 0");
    apply(new DepositMoneyEvent(command.getHolderID(), command.getAccountID(), command.getAmount()));
  }

  @EventSourcingHandler
  protected void depositMoney(DepositMoneyEvent event){
    log.debug("applying {}",event);
    this.balance += event.getAmount();
    log.debug("balance {}",this.balance);
  }

  @CommandHandler
  protected void withdrawMoney(WithdrawMoneyCommand command){
    log.debug("handling {}",command);
    if(this.balance - command.getAmount() < 0) throw new IllegalStateException("잔고가 부족합니다.");
    else if(command.getAmount() <= 0 ) throw new IllegalStateException("amount >= 0");
    apply(new WithdrawMoneyEvent(command.getHolderID(), command.getAccountID(), command.getAmount()));
  }
  @EventSourcingHandler
  protected void withdrawMoney(WithdrawMoneyEvent event){
    log.debug("applying {}",event);
    this.balance -= event.getAmount();
    log.debug("balance {}",this.balance);
  }
}
