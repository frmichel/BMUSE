package hwu.elixir.scrape.db.crawl;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import hwu.elixir.utils.Validation;


/**
 * 
 * Store the current status of a single URL in the scrape service.
 * 
 *
 */


@Entity
public class CrawlRecord {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String context = "";

	@Column(unique = true, length = 250)
	private String url;

	private Date dateScraped;

	@Enumerated(EnumType.STRING)
	private StatusOfScrape status;
	
	@Column(columnDefinition = "boolean default false")
	private boolean beingScraped;

	public CrawlRecord() {		
		status = StatusOfScrape.UNTRIED;
	}

	public CrawlRecord(String url) {
		Validation validation = new Validation();
		if(validation.validateURI(url)) {
			this.url = url;
			context = "";
			status = StatusOfScrape.UNTRIED;
			dateScraped = null;
		} else {
			throw new IllegalArgumentException(url +" is not a valid url");
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public Date getDateScraped() {			
		return dateScraped;
	}

	public void setDateScraped(Date dateScraped) {
		this.dateScraped = dateScraped;
	}

	public StatusOfScrape getStatus() {
		return status;
	}

	public void setStatus(StatusOfScrape status) {
		this.status = status;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}
	
	public boolean isBeingScraped() {
		return beingScraped;
	}

	public void setBeingScraped(boolean beingScraped) {
		this.beingScraped = beingScraped;
	}	

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof CrawlRecord))
			return false;

		CrawlRecord otherCrawl = (CrawlRecord) o;

		if(this.url.equals(otherCrawl.getUrl())) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int result = getId() != null ? getId().hashCode() : 0;
		result = 31 * result + (getUrl() != null ? getUrl().hashCode() : 0);
		result = 31 * result + (getContext() != null ? getContext().hashCode() : 0);
		result = 31 * result + (getDateScraped() != null ? getDateScraped().hashCode() : 0);
		return result;
	}

}
