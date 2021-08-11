<?xml version="1.0" encoding="ISO-8859-15"?>
<!--
  Copyright 2020 Stefan Holm
  
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
  
      http://www.apache.org/licenses/LICENSE-2.0
  
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
-->
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">
	<xsl:output method="xml" version="1.0" omit-xml-declaration="no" indent="yes" />
	<xsl:param name="versionParam" select="'1.0'" />
	
	<!-- ========================= -->
	<!-- root element: kalkulation -->
	<!-- ========================= -->
	<xsl:template match="kalkulation">
		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format" font-family="Helvetica">
			<fo:layout-master-set>
				<fo:simple-page-master master-name="simpleA4" page-height="29.7cm" page-width="21cm" margin-top="2cm" margin-bottom="0.8cm" margin-left="2cm" margin-right="2cm">
					<fo:region-body region-name="xsl-region-body" margin-bottom="2cm" margin-top="0cm"/>>
<!-- 					<fo:region-before region-name="xsl-region-before" extent="0cm"/> -->
            		<fo:region-after region-name="xsl-region-after" extent="1cm"/>
				</fo:simple-page-master>
			</fo:layout-master-set>

			<fo:page-sequence master-reference="simpleA4">
			
<!-- 				<fo:static-content flow-name="xsl-region-before"> -->
<!-- 						<fo:block>header</fo:block> -->
<!-- 				</fo:static-content> -->
				
				<fo:static-content flow-name="xsl-region-after">
						<fo:block font-size="8pt" text-align="right">
							<xsl:value-of select="info/creationdate"/>
						</fo:block>
				</fo:static-content>
        
				<fo:flow flow-name="xsl-region-body">
				
					<fo:block font-size="16pt" font-weight="bold" space-after="5mm">
						<xsl:value-of select="pdftitles/haupttitel"/> '<xsl:value-of select="modellname" />'
					</fo:block>
					
					<fo:block font-size="10pt">
						<fo:table table-layout="fixed" width="100%"	border-collapse="separate">
							<fo:table-column column-width="9.5cm" />
							<fo:table-column column-width="0.5cm" />
							<fo:table-column column-width="7.0cm" />
							<fo:table-body>

								<xsl:if test="info/arbeitsort">
									<fo:table-row>
										<fo:table-cell>
											<fo:block font-weight="bold"> <xsl:value-of select="pdftitles/arbeitsort"/> </fo:block>
										</fo:table-cell>
									
										<fo:table-cell number-columns-spanned="2">
											<fo:block> <xsl:value-of select="info/arbeitsort" /> </fo:block>
										</fo:table-cell>
									</fo:table-row>

									<fo:table-row>
										<fo:table-cell>
											<fo:block>&#160;</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</xsl:if>
							
								<fo:table-row>
									<fo:table-cell>
										<fo:block font-weight="bold"> <xsl:value-of select="pdftitles/arbeitsobjekt"/> </fo:block>
									</fo:table-cell>
								</fo:table-row>

								<xsl:apply-templates select="arbeitsobjekt/entry" />

								<fo:table-row>
									<fo:table-cell>
										<fo:block>&#160;</fo:block>
									</fo:table-cell>
								</fo:table-row>

								<fo:table-row>
									<fo:table-cell>
										<fo:block font-weight="bold"> <xsl:value-of select="pdftitles/arbeitssystem"/> </fo:block>
									</fo:table-cell>
								</fo:table-row>

								<xsl:apply-templates select="arbeitssystem/entry" />

								<fo:table-row>
									<fo:table-cell>
										<fo:block>&#160;</fo:block>
									</fo:table-cell>
								</fo:table-row>

								<fo:table-row>
									<fo:table-cell>
										<fo:block font-weight="bold"> <xsl:value-of select="pdftitles/faktoren"/> </fo:block>
									</fo:table-cell>
								</fo:table-row>
								
								<xsl:apply-templates select="faktoren/entry" />

								<fo:table-row>
									<fo:table-cell>
										<fo:block>&#160;</fo:block>
									</fo:table-cell>
								</fo:table-row>

								<fo:table-row>
									<fo:table-cell>
										<fo:block>&#160;</fo:block>
									</fo:table-cell>
								</fo:table-row>

							</fo:table-body>
						</fo:table>
					</fo:block>
					
					<fo:block font-size="10pt">
						<fo:table table-layout="fixed" width="100%"	border-collapse="separate">
							<fo:table-column column-width="7cm" />
							<fo:table-column column-width="3cm" />
							<fo:table-column column-width="1cm" />
							<fo:table-column column-width="3cm" />
							<fo:table-column column-width="3cm" />
							<fo:table-body>

								<fo:table-row>
									<fo:table-cell number-columns-spanned="5">
										<fo:block font-weight="bold"> <xsl:value-of select="pdftitles/ergebnis"/> <xsl:value-of select="pdftitles/ergebnisSuffix"/> </fo:block>
									</fo:table-cell>
								</fo:table-row>
								
								<xsl:apply-templates select="ergebnis/*" />

							</fo:table-body>
						</fo:table>
					</fo:block>
					
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
	<!-- ========================= -->
	<!-- child element: entry -->
	<!-- ========================= -->
	<xsl:template match="entry">
	
		<fo:table-row>
				
			<fo:table-cell>
				<fo:block>
					<xsl:value-of select="label" />
				</fo:block>
			</fo:table-cell>
			
			<fo:table-cell>
				<fo:block>&#160;</fo:block>
			</fo:table-cell>
			
			<fo:table-cell>
				<fo:block>
					<xsl:value-of select="value" />
				</fo:block>
			</fo:table-cell>
					
		</fo:table-row>
		
	</xsl:template>
	
	<xsl:template match="ergebniszeile">
	
		<fo:table-row>
				
			<fo:table-cell>
				<fo:block>
					<xsl:value-of select="spalte1" />
				</fo:block>
			</fo:table-cell>
			
			<fo:table-cell text-align="right">
				<fo:block>
					<xsl:value-of select="spalte2" />
				</fo:block>
			</fo:table-cell>
			
			<fo:table-cell>
				<fo:block margin-left="5pt">
					<xsl:value-of select="spalte3" />
				</fo:block>
			</fo:table-cell>
			
			<fo:table-cell text-align="right">
			
				<xsl:if test="spalte4/b">
					<fo:block font-weight="bold">
						<xsl:value-of select="spalte4" />
					</fo:block>
				</xsl:if>
				<xsl:if test="not(spalte4/b)">
					<fo:block>
						<xsl:value-of select="spalte4" />
					</fo:block>
				</xsl:if>
				
			</fo:table-cell>
			
			<fo:table-cell text-align="right">
				<fo:block>
					<xsl:value-of select="spalte5" />
				</fo:block>
			</fo:table-cell>
					
		</fo:table-row>
		
	</xsl:template>
	
	<xsl:template match="leerzeile">
	
		<fo:table-row>
			<fo:table-cell>
				<fo:block>&#160;</fo:block>
			</fo:table-cell>
		</fo:table-row>
		
	</xsl:template>
</xsl:stylesheet>
