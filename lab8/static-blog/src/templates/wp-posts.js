import { graphql } from 'gatsby';
import React from 'react';

import { StaticImage } from "gatsby-plugin-image"
import TemplateWrapper from '../layouts/index'

import { DiscussionEmbed } from "disqus-react"

export const query = graphql`
    query WpPostQuery($id: String!) {
        wpPost(id: { eq: $id }) {
                  author {
                    node {
                      firstName
                      lastName
                    }
                  }
                  content
                  date
                  uri
                  id
                  title
        }
    }
`;

export default ({ data }) => {
    let post = data.wpPost;
    const disqusConfig = {
        shortname: 'lab8-1',
        config: { identifier: post.id, title: post.title },
    }

    return (
        <TemplateWrapper>
            <div>
                <StaticImage src="../images/gatsby-astronaut.png" alt="An astronaut" />
                <div>
                    <h1>{post.title}</h1>
                    <div dangerouslySetInnerHTML={{ __html: post.content }} />
                </div>
                <DiscussionEmbed {...disqusConfig} />
            </div>
        </TemplateWrapper>
    );
};