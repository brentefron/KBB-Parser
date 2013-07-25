import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Purpose: Parser Class will Parse KBB.com for car data
 * 
 * @author Brent Efron
 * @author Christopher Wong
 * @version 1.0 7/24/13
 */
public class Parser {
	/**
	 * Generates the ArrayList<ArrayList<String>> carData of data Parsed from KBB.com
	 * 
	 * @return ArrayList<ArrayList<String>> of {Classification (Make | Model | Year | Trim), Engine type, Body Style, Transmission, Drive Train, Seating, MPG City/Highway, MSRP}
	 */
	public ArrayList<ArrayList<String>> carData() {
		Document sitemap;
		Document makes;
		Document years;
		Document models;
		Document infos;
		Document bodyStyles;
		Document trims;
		Document options;
		Document specs;
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		Integer i = 0;
		Integer j = 0;

		//companies
		try {

			//need http protocol
			sitemap = Jsoup.connect("http://kbb.com/sitemap").userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
					.referrer("http://www.google.com")
					.get();

			//get page title
			System.out.println("KBB Cars");
			System.out.println("");

			//get column
			Elements column = sitemap.select("div.grid-4:contains(Acura)" +
					"");
			Elements names = column.select("div.box-content-b");
			Elements links = names.select("a[href]");

			for (Element link : links) {

				// get the value from href attribute

				makes = Jsoup.connect("http://kbb.com" + (link.attr("href"))).timeout(0).userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
						.referrer("http://www.google.com")
						.get();
				Elements modelColumn = makes.select("div#Model-select.mod-inv");
				Elements modelBoxes = modelColumn.select("div.model-year-wrapper");
				Elements modelNameLocations = modelBoxes.select("div.left");
				Elements modelNames = modelNameLocations.select("a.section-title");
				Elements modelLinks = modelNames.select("a[href]");
				String title = makes.title();
				System.out.println("Make : " + title.substring(0, title.length() - 19));
				for (Element modelLink : modelLinks) {

					models = Jsoup.connect("http://kbb.com" + (modelLink.attr("href"))).timeout(0).userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
							.referrer("http://www.google.com")
							.get();
					Elements yearColumn = models.select("div#Model-year-select.mod-inv");
					Elements yearBoxes = yearColumn.select("div.model-year");
					Elements yearNameLocations = yearBoxes.select("div.left");
					Elements yearNames = yearNameLocations.select("a.section-title");
					Elements yearLinks = yearNames.select("a[href]");

					if (yearLinks.hasText()) {
						for (Element yearLink : yearLinks) {
							years = Jsoup.connect("http://kbb.com" + (yearLink.attr("href"))).timeout(0).userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
									.referrer("http://www.google.com")
									.get();
							Elements infolocations = years.select("div#Cta-container.mod-primary");
							Elements infoboxes = infolocations.select("span.right");
							Elements infobuttons = infoboxes.select("a.btn-main-cta");
							Elements infolinks = infobuttons.select("[href]");

							if (infobuttons.text().equals("See what you should pay")) {
								infos = Jsoup.connect("http://kbb.com" + (infolinks.attr("href"))).timeout(0).userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
										.referrer("http://www.google.com")
										.get();
								Elements trimsections = infos.select("div.vehicle-styles-head.accordion-sub");
								if (trimsections.hasText()) {
									for (Element trimsection: trimsections) {
										if (trimsection.text().contains("Not Sure?")) {
											break;
										}
										else {
											data.add(new ArrayList<String>());	
											if (trimsection.text().contains("Lowest priced style")) {
												System.out.println(link.text() + " | " + modelLink.text().substring(link.text().length()+1) + " | " + yearLink.text().substring(0,4) + " | " + trimsection.text().substring(0,trimsection.text().length()-40));
												String columnOne = link.text() + " | " + modelLink.text().substring(link.text().length()+1) + " | " + yearLink.text().substring(0,4) + " | " + trimsection.text().substring(0,trimsection.text().length()-40) + "#";
												data.get(i).add(columnOne);
												i++;
											}
											else {
												System.out.println(link.text() + " | " + modelLink.text().substring(link.text().length()+1) + " | " + yearLink.text().substring(0,4) + " | " + trimsection.text().substring(0,trimsection.text().length()-18));
												String columnOne = link.text() + " | " + modelLink.text().substring(link.text().length()+1) + " | " + yearLink.text().substring(0,4) + " | " + trimsection.text().substring(0,trimsection.text().length()-18) + "#";
												data.get(i).add(columnOne);
												i++;
											}
											Elements trimLink = trimsection.select("a[href]");
											trims = Jsoup.connect("http://kbb.com" + (trimLink.attr("href"))).timeout(0).userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
													.referrer("http://www.google.com")
													.get();
											Elements optionsSection = trims.select("p.section-subtitle");
											Elements optionButton = optionsSection.select("a.btn-secondary-cta");
											Elements optionLink = optionButton.select("a[href]");
											options = Jsoup.connect("http://kbb.com" + (optionLink.attr("href"))).timeout(0).userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
													.referrer("http://www.google.com")
													.get();

											//MSRP
											Elements msrpBox = options.select("li#msrp.msrp");
											Elements msrpText = msrpBox.select("span.item-value.green");
											System.out.println(msrpText.text());
											Elements specsButton = options.select("li:contains(specs)");
											Elements specsLink = specsButton.select("a[href]");

											//How to navigate to page
											specs = Jsoup.connect("http://kbb.com" + (specsLink.attr("href"))).timeout(0).userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
													.referrer("http://www.google.com")
													.get();

											//MPG City/Highway (Specs-Highlights)
											Elements mpgBox = specs.select("span.section-subtitle:contains(MPG:)");
											Elements mpgText = mpgBox.select("span.highlight");

											//Engine type (Specs-Highlights)
											Elements engineBox = specs.select("span.section-subtitle:contains(Engine:)");
											Elements engineText = engineBox.select("span.highlight");

											//Body Style (Specs-Highlights)
											Elements bodyBox = specs.select("span.section-subtitle:contains(Body Style:)");
											Elements bodyText = bodyBox.select("span.highlight");

											//Transmission (Specs-Highlights)
											Elements transmissionBox = specs.select("span.section-subtitle:contains(Transmission:)");
											Elements transmissionText = transmissionBox.select("span.highlight");

											//Drive Train (Specs-Highlights)
											Elements driveTrainBox = specs.select("span.section-subtitle:contains(Drivetrain:)");
											Elements driveTrainText = driveTrainBox.select("span.highlight");

											//Seating (Specs-Highlights)
											Elements seatingBox = specs.select("span.section-subtitle:contains(Seating:)");
											Elements seatingText = seatingBox.select("span.highlight");

											//adds to data ArrayList
											data.get(j).add(engineText.text()+ "#");
											data.get(j).add(bodyText.text()+ "#");
											data.get(j).add(transmissionText.text()+ "#");
											data.get(j).add(driveTrainText.text()+ "#");
											data.get(j).add(seatingText.text()+ "#");
											data.get(j).add(mpgText.text()+ "#");
											data.get(j).add(msrpText.text());
											j++;
										}		
									}
								}
								else {
									Elements bodyStyleSection = infos.select("div.mod-category-inner");
									Elements bodyStyleLinks = bodyStyleSection.select("a.section-title.with-sub");
									for (Element bodyStyleLink: bodyStyleLinks) {
										bodyStyles = Jsoup.connect("http://kbb.com" + (bodyStyleLink.attr("href"))).timeout(0).userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
												.referrer("http://www.google.com")
												.get();
										Elements trimSections2 = bodyStyles.select("div.vehicle-styles-head.accordion-sub");

										for (Element trimSection2: trimSections2) {
											if (trimSection2.text().contains("Not Sure?")) {
												break;
											}
											else {
												data.add(new ArrayList<String>());
												if (trimSection2.text().contains("Lowest priced style")) {
													System.out.println(link.text() + " | " + modelLink.text().substring(link.text().length()+1) + " | " + yearLink.text().substring(0,4) + " | "  + bodyStyleLink.text() + " | " + trimSection2.text().substring(0,trimSection2.text().length()-40));
													String columnOne = link.text() + " | " + modelLink.text().substring(link.text().length()+1) + " | " + yearLink.text().substring(0,4) + " | "  + bodyStyleLink.text() + " | " + trimSection2.text().substring(0,trimSection2.text().length()-40) + "#";
													data.get(i).add(columnOne);
													i++;
												}
												else {
													System.out.println(link.text() + " | " + modelLink.text().substring(link.text().length()+1) + " | " + yearLink.text().substring(0,4) + " | "  + bodyStyleLink.text() + " | " + trimSection2.text().substring(0,trimSection2.text().length()-18));
													String columnone = link.text() + " | " + modelLink.text().substring(link.text().length()+1) + " | " + yearLink.text().substring(0,4) + " | "  + bodyStyleLink.text() + " | " + trimSection2.text().substring(0,trimSection2.text().length()-18) + "#";
													data.get(i).add(columnone);
													i++;
												}
												Elements trimlink = trimSection2.select("a[href]");
												trims = Jsoup.connect("http://kbb.com" + (trimlink.attr("href"))).timeout(0).userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
														.referrer("http://www.google.com")
														.get();
												Elements optionsSection = trims.select("p.section-subtitle");
												Elements optionButton = optionsSection.select("a.btn-secondary-cta");
												Elements optionlink = optionButton.select("a[href]");
												options = Jsoup.connect("http://kbb.com" + (optionlink.attr("href"))).timeout(0).userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
														.referrer("http://www.google.com")
														.get();
												//MSRP
												Elements msrpBox = options.select("li#msrp.msrp");
												Elements msrpText = msrpBox.select("span.item-value.green");
												System.out.println(msrpText.text());
												Elements specsButton = options.select("li:contains(specs)");
												Elements specsLink = specsButton.select("a[href]");

												//How to navigate to page
												specs = Jsoup.connect("http://kbb.com" + (specsLink.attr("href"))).timeout(0).userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
														.referrer("http://www.google.com")
														.get();

												//MPG City/Highway (Specs-Highlights)
												Elements mpgBox = specs.select("span.section-subtitle:contains(MPG:)");
												Elements mpgText = mpgBox.select("span.highlight");

												//Engine type (Specs-Highlights)
												Elements engineBox = specs.select("span.section-subtitle:contains(Engine:)");
												Elements engineText = engineBox.select("span.highlight");

												//Body Style (Specs-Highlights)
												Elements bodyBox = specs.select("span.section-subtitle:contains(Body Style:)");
												Elements bodyText = bodyBox.select("span.highlight");

												//Transmission (Specs-Highlights)
												Elements transmissionBox = specs.select("span.section-subtitle:contains(Transmission:)");
												Elements transmissionText = transmissionBox.select("span.highlight");

												//Drive Train (Specs-Highlights)
												Elements driveTrainBox = specs.select("span.section-subtitle:contains(Drivetrain:)");
												Elements driveTrainText = driveTrainBox.select("span.highlight");

												//Seating (Specs-Highlights)
												Elements seatingBox = specs.select("span.section-subtitle:contains(Seating:)");
												Elements seatingText = seatingBox.select("span.highlight");

												//adds to data ArrayList
												data.get(j).add(engineText.text()+ "#");
												data.get(j).add(bodyText.text()+ "#");
												data.get(j).add(transmissionText.text()+ "#");
												data.get(j).add(driveTrainText.text()+ "#");
												data.get(j).add(seatingText.text()+ "#");
												data.get(j).add(mpgText.text()+ "#");
												data.get(j).add(msrpText.text());
												j++;
											}		
										}
									}																			
								}
							}
							else {
								break;
							}
						}
					}
					else {
						years = Jsoup.connect("http://kbb.com" + (modelLink.attr("href"))).timeout(0).userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
								.referrer("http://www.google.com")
								.get();
						Elements trimSections = years.select("div.vehicle-styles-head.accordion-sub");
						if (trimSections.hasText()) {
							Elements yearTitle = years.select("h2.section-title.white.with-module");
							for (Element trimSection: trimSections) {
								if (trimSection.text().contains("Not Sure?")) {
									break;
								}
								else {
									data.add(new ArrayList<String>());
									if (trimSection.text().contains("Lowest priced style")) {
										System.out.println(link.text() + " | " + modelLink.text().substring(link.text().length()+1) + " | " + yearTitle.text().substring(0,4) + " | " + trimSection.text().substring(0,trimSection.text().length()-40));
										String columnOne = link.text() + " | " + modelLink.text().substring(link.text().length()+1) + " | " + yearTitle.text().substring(0,4) + " | " + trimSection.text().substring(0,trimSection.text().length()-40) + "#";
										data.get(i).add(columnOne);
										i++;
									}
									else {
										System.out.println(link.text() + " | " + modelLink.text().substring(link.text().length()+1) + " | " + yearTitle.text().substring(0,4) + " | " + trimSection.text().substring(0,trimSection.text().length()-18));
										String columnOne = link.text() + " | " + modelLink.text().substring(link.text().length()+1) + " | " + yearTitle.text().substring(0,4) + " | " + trimSection.text().substring(0,trimSection.text().length()-18) + "#";
										data.get(i).add(columnOne);
										i++;
									}
									Elements trimLink = trimSection.select("a[href]");
									trims = Jsoup.connect("http://kbb.com" + (trimLink.attr("href"))).timeout(0).userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
											.referrer("http://www.google.com")
											.get();
									Elements optionsSection = trims.select("p.section-subtitle");
									Elements optionButton = optionsSection.select("a.btn-secondary-cta");
									Elements optionLink = optionButton.select("a[href]");
									options = Jsoup.connect("http://kbb.com" + (optionLink.attr("href"))).timeout(0).userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
											.referrer("http://www.google.com")
											.get();
									//MSRP
									Elements msrpBox = options.select("li#msrp.msrp");
									Elements msrpText = msrpBox.select("span.item-value.green");
									System.out.println(msrpText.text());
									Elements specsButton = options.select("li:contains(specs)");
									Elements specsLink = specsButton.select("a[href]");

									//How to navigate to page
									specs = Jsoup.connect("http://kbb.com" + (specsLink.attr("href"))).timeout(0).userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
											.referrer("http://www.google.com")
											.get();

									//MPG City/Highway (Specs-Highlights)
									Elements mpgBox = specs.select("span.section-subtitle:contains(MPG:)");
									Elements mpgText = mpgBox.select("span.highlight");

									//Engine type (Specs-Highlights)
									Elements engineBox = specs.select("span.section-subtitle:contains(Engine:)");
									Elements engineText = engineBox.select("span.highlight");

									//Body Style (Specs-Highlights)
									Elements bodyBox = specs.select("span.section-subtitle:contains(Body Style:)");
									Elements bodyText = bodyBox.select("span.highlight");

									//Transmission (Specs-Highlights)
									Elements transmissionBox = specs.select("span.section-subtitle:contains(Transmission:)");
									Elements transmissionText = transmissionBox.select("span.highlight");

									//Drive Train (Specs-Highlights)
									Elements driveTrainBox = specs.select("span.section-subtitle:contains(Drivetrain:)");
									Elements driveTrainText = driveTrainBox.select("span.highlight");

									//Seating (Specs-Highlights)
									Elements seatingBox = specs.select("span.section-subtitle:contains(Seating:)");
									Elements seatingText = seatingBox.select("span.highlight");

									//adds to data ArrayList
									data.get(j).add(engineText.text()+ "#");
									data.get(j).add(bodyText.text()+ "#");
									data.get(j).add(transmissionText.text()+ "#");
									data.get(j).add(driveTrainText.text()+ "#");
									data.get(j).add(seatingText.text()+ "#");
									data.get(j).add(mpgText.text()+ "#");
									data.get(j).add(msrpText.text());
									j++;
								}		
							}
						}
					}
				}
			}			
			System.out.println(" ");
		} 	
		catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
}
