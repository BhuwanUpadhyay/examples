package example.domain.model;

import example.domain.commands.FailedEvent;
import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Access(AccessType.FIELD)
public class FailedOrder implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String messageId;
  private String messageQueue;
  private String messageExchange;
  private Long messageTimestamp;
  @Column(length = 10485760)
  private String messagePayload;
  @Column(length = 10485760)
  private String messageFailureReason;
  @Enumerated(EnumType.STRING)
  private Status status;

  public FailedOrder() {
  }

  public FailedOrder(FailedEvent event) {
    this.messageExchange = event.exchange();
    this.messageQueue = event.queue();
    this.messageFailureReason = event.reason();
    this.messageId = event.id().toString();
    this.messagePayload = event.payload();
    this.messageTimestamp = event.timestamp();
    this.status = Status.INITIAL;
  }

  public String getMessageId() {
    return messageId;
  }

  public String getMessageQueue() {
    return messageQueue;
  }

  public String getMessageExchange() {
    return messageExchange;
  }

  public Long getMessageTimestamp() {
    return messageTimestamp;
  }

  public String getMessagePayload() {
    return messagePayload;
  }

  public String getMessageFailureReason() {
    return messageFailureReason;
  }

  public Status getStatus() {
    return status;
  }

  public enum Status {
    INITIAL, RETURNED, ARCHIVED, DISCARDED
  }
}
