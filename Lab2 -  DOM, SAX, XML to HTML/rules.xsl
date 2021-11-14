<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/softwareList">
        <html>
            <body>
                <table border="1">
                    <tr>
                        <th>Title</th>
                        <th>Annotation</th>
						<th>Type</th>
						<th>Version</th>
                        <th>Author</th>
						<th>TermsOfUse</th>
                        <th>Location</th>
                    </tr>
                    <xsl:for-each select="software">
                        <tr>
                            <td>
                                <xsl:value-of select="title" />
                            </td>
                            <td>
                                <xsl:value-of select="annotation" />
                            </td>
							<td>
                                <xsl:value-of select="type" />
                            </td>
							<td>
                                <xsl:value-of select="version" />
                            </td>
                            <td>
                                <xsl:value-of select="author" />
                            </td>
                            <td>
                                <xsl:value-of select="termsOfUse" />
                            </td>
                            <td>
                                <xsl:value-of select="location" />
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>